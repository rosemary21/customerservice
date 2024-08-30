package interswitch.assessment.customerservice.auth.filter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import javax.xml.bind.DatatypeConverter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

public class Jwt {

    @Value("${secret.key}")
    private static  String secretKey;

    @Value("${jwt.millseconds}")
    private static String expirationTime;

    @PostConstruct
    protected void init(){
        secretKey= Base64.getEncoder().encodeToString("key".getBytes());
    }


    public static String defaultCustomerToken(String username,String bvn){
        Date dt = new Date();
        Date tomorrow = new Date(dt.getTime() + (1000 * 60 * 60 * 24));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        String base64Encoded = DatatypeConverter.printBase64Binary("key".getBytes());
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Encoded);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder().setId(bvn)
                .setExpiration(tomorrow)
                .setSubject(username)
                .setIssuer("Customer")

                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();

    }

}
