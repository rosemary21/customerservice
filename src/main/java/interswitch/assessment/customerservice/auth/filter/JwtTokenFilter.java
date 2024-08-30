package interswitch.assessment.customerservice.auth.filter;

import interswitch.assessment.customerservice.auth.filter.token.JwtResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class JwtTokenFilter  extends OncePerRequestFilter {


    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        DefaultFilter defaultFilter = new DefaultFilter();
        log.info("getting the url {}", url);
        boolean value = defaultFilter.getDefaultFilter(url);
        log.info("getting the value of filter {}",value);
        if (value) {
            filterChain.doFilter(request, httpServletResponse);
        } else {
            JwtResponse result = jwtTokenProvider.validateToken(request.getHeader("apiKey"));
            if (result.isResult()) {
                if (result.getRole().equalsIgnoreCase("Customer")) {
                    Authentication auth = jwtTokenProvider.getAuthenticationCustomer(request.getHeader("apiKey"));
                    if (!Objects.isNull(auth))
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    filterChain.doFilter(request, httpServletResponse);
                }

            }
            if (!result.isResult()) {
                log.info("Authentication is invalid {}", result.isResult());
                JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
                jwtAuthenticationEntryPoint.commence(request, httpServletResponse, null);
            }
        }

    }

}
