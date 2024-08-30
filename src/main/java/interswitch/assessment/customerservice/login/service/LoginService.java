package interswitch.assessment.customerservice.login.service;


import interswitch.assessment.customerservice.login.dto.LoginParam;
import interswitch.assessment.customerservice.login.resp.LoginResponseData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;

public interface LoginService {
    LoginResponseData login(LoginParam loginParam, HttpSession session, HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
