package interswitch.assessment.customerservice.account.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Where(clause ="del_Flag='N'")
public class Account extends AbstractEntity {

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
    private BigDecimal balance;
}
