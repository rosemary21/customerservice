package interswitch.assessment.customerservice.billpayment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Authorization {

    private String transfer_reference;
    private String transfer_account;
    private String transfer_bank;
    private Long account_expiration;
    private String transfer_note;
    private String transfer_amount;
    private String mode;
}
