package interswitch.assessment.customerservice.login.service.implementation;

import interswitch.assessment.customerservice.account.model.Account;
import interswitch.assessment.customerservice.account.res.AccountResp;
import interswitch.assessment.customerservice.account.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationSuccessEventListener extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private AccountService cuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)throws IOException, ServletException {

        logger.info("email: " + request.getAttribute("phoneNumber"));
        AccountResp user = cuService.getAccount(request.getAttribute("phoneNumber").toString());

    }

}
