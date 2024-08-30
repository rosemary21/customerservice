package interswitch.assessment.customerservice.login.controller;


import interswitch.assessment.customerservice.account.req.AccountReq;
import interswitch.assessment.customerservice.account.res.AccountResp;
import interswitch.assessment.customerservice.account.service.AccountService;
import interswitch.assessment.customerservice.auth.filter.Jwt;
import interswitch.assessment.customerservice.login.dto.LoginParam;
import interswitch.assessment.customerservice.login.resp.LoginResponseData;
import interswitch.assessment.customerservice.login.service.LoginService;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*
This is the service that login the user
 */

@Slf4j
@RestController
@RequestMapping(value = {"/api/v1/login"}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoginController {


    @Autowired
    LoginService loginService;

    /**
     * This service login the user after the user has entered the right password and phone number
     * @param accountReq
     * @param session
     * @param request
     * @param response
     * @param authentication
     * @return
     */
    @ApiOperation(value = "Add Account Service", response = AccountResp.class, notes = "This is use to Account Service")
    @RequestMapping(value = {"/customer"}, method = RequestMethod.POST)
    private ResponseEntity<LoginResponseData> addAccount(@RequestBody LoginParam accountReq, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        var loginResponseData = loginService.login(accountReq,session,request,response,authentication);
        if(loginResponseData.getResp().getCode().equalsIgnoreCase("is00")){
            String tokenValue= Jwt.defaultCustomerToken(accountReq.getPhoneNumber(),loginResponseData.getBvn());
            loginResponseData.setToken(tokenValue);
            return new ResponseEntity<>(loginResponseData, HttpStatus.OK);
        }
        return new ResponseEntity<LoginResponseData>(loginResponseData, HttpStatus.BAD_REQUEST);
    }
}
