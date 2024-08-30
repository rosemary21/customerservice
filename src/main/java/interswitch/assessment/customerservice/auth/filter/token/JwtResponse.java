package interswitch.assessment.customerservice.auth.filter.token;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {
    private boolean result;
    private String role;
    private String bvn;
}
