package interswitch.assessment.customerservice.bvn.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BvnDto {

    private String bvn;
    private String userId;
    private String bvnPhoneNumber;
    private String dob;
    private String otp;
}
