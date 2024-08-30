package interswitch.assessment.customerservice.auth.filter;

import interswitch.assessment.customerservice.auth.filter.token.JwtResponse;
import interswitch.assessment.customerservice.login.dto.CustomUserDetails;
import interswitch.assessment.customerservice.login.service.implementation.CustomerUserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private static final Logger LOGGER= LoggerFactory.getLogger(JwtTokenProvider.class);


    @Autowired
    CustomerUserDetailsServiceImpl customUserDetailsService;


    @Autowired
    private ModelMapper mm;

    private String secretKey;


    public Authentication getAuthenticationCustomer(String token){
        CustomUserDetails userDetails=this.customUserDetailsService.loadUserByUsername(getUsername(token));
        return  new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }




    private String getUsername(String token){
        secretKey= Base64.getEncoder().encodeToString("key".getBytes());
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    /**
     * This is use to validate the JWT token enster and verify if the JWT token entered is correct
     * @param token
     * @return
     */
    public JwtResponse validateToken(String token){
        try{
            log.info("getting the token {}",token);
            secretKey= Base64.getEncoder().encodeToString("key".getBytes());
            Jws<Claims> claimsJws=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setRole(claimsJws.getBody().getIssuer());
            jwtResponse.setResult(true);
            jwtResponse.setBvn(claimsJws.getBody().getId());
            log.info("getting the claims {}",claimsJws.getBody().getExpiration());
            return jwtResponse;
        }
        catch(JwtException  | IllegalArgumentException exception){
            exception.printStackTrace();
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setResult(false);
            return  jwtResponse;
        }
    }






}
