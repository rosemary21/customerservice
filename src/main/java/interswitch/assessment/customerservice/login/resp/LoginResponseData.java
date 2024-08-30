package interswitch.assessment.customerservice.login.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseData {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String token;
    private String validDevice;
    private String stage;
    private String bvn;

    private ResponseDto resp;
}
