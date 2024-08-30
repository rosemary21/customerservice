package interswitch.assessment.customerservice.login.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginParam {

    private String emailAddress;
    private String password;
    private String phoneNumber;
    private String deviceId;
    private String deviceName;
}
