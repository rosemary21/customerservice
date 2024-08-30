package interswitch.assessment.customerservice.account.res;

import interswitch.assessment.customerservice.account.dto.AccountDto;
import interswitch.assessment.customerservice.account.model.Account;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountResp {
    private String status;
    private String message;
    private Account data;
    private AccountDto accountDetails;
}
