package interswitch.assessment.customerservice.account.req;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountReq {

    private String accountNumber;
    private String accountName;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String gender;
    private String dateOfBirth;
    @Column(unique = true)
    private String bvn;
    private String password;


}
