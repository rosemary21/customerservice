package interswitch.assessment.customerservice.login.service.implementation;

import interswitch.assessment.customerservice.account.dto.AccountDto;
import interswitch.assessment.customerservice.account.model.Account;
import interswitch.assessment.customerservice.account.repository.AccountRepository;
import interswitch.assessment.customerservice.account.res.AccountResp;
import interswitch.assessment.customerservice.account.service.AccountService;
import interswitch.assessment.customerservice.login.dto.CustomUserDetails;
import interswitch.assessment.customerservice.login.dto.LoginParam;
import interswitch.assessment.customerservice.login.resp.LoginResponseData;
import interswitch.assessment.customerservice.login.resp.ResponseDto;
import interswitch.assessment.customerservice.login.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Locale;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Autowired
    CustomerUserDetailsServiceImpl customerLoginService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountService accountService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AuthenticationSuccessEventListener successEventListener;


    @Override
    public LoginResponseData login(LoginParam loginParam, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        LoginResponseData lrData = new LoginResponseData();
        ResponseDto resp = new ResponseDto();
        try {

            log.info("About authenticate the login details ");
            AccountResp user = authenticateUser(loginParam,session,request,response,authentication);

            if(null != user){

                lrData=     modelMapper.map(user,LoginResponseData.class);

                log.info("successfully authenticated");
                resp.setCode(messageSource.getMessage("service.success.code",null, Locale.ENGLISH));
                resp.setMessage(messageSource.getMessage("login.success.message",null,Locale.ENGLISH));
                lrData.setResp(resp);
                return lrData;
            }
            else {
                resp.setCode(messageSource.getMessage("service.error.code",null, Locale.ENGLISH));
                resp.setMessage(messageSource.getMessage("login.failed.message",null,Locale.ENGLISH));
                lrData.setResp(resp);
                return lrData;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setCode(messageSource.getMessage("service.exception.code",null, Locale.ENGLISH));
            resp.setMessage(messageSource.getMessage("login.exception.message",null,Locale.ENGLISH));
            lrData.setResp(resp);
        }

        return lrData;
    }


    public AccountResp authenticateUser(LoginParam loginParam, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {

            UserDetails ud = customerLoginService.loadUserByUsername(loginParam.getPhoneNumber());
            if(ud == null || !passwordEncoder.matches(loginParam.getPassword(),ud.getPassword())) {
                log.info("User authentication is unsuccessful...");
                return null;
            }
            AdminUsernamePasswordAuthenticationToken token = new AdminUsernamePasswordAuthenticationToken(ud,loginParam.getPassword(),ud.getAuthorities());
            DaoAuthenticationProvider authenticationProviders=new DaoAuthenticationProvider();
            authenticationProviders.setUserDetailsService(customerLoginService);
            authenticationProviders.setPasswordEncoder(passwordEncoder);
            Authentication auth= authenticationProviders.authenticate(token);
            log.info("Authenticating staff {}",auth);
            if(auth.isAuthenticated()){
                log.info("getting the login param {}",loginParam.getPhoneNumber());
                AccountResp user = accountService.getAccountByPhoneNumber(loginParam.getPhoneNumber());
                SecurityContextHolder.getContext().setAuthentication(token);
                log.info("User successfully logged in -> username: {} ", loginParam.getPhoneNumber());
                session.setAttribute("user", user);
                request.setAttribute("phoneNumber",loginParam.getPhoneNumber());
                successEventListener.onAuthenticationSuccess(request, response,authentication);
                return user;
            }else {
                return null;
            }

        }

        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
