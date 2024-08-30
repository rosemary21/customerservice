package interswitch.assessment.customerservice.account.service;

import interswitch.assessment.customerservice.account.req.AccountReq;
import interswitch.assessment.customerservice.account.res.AccountResp;

public interface AccountService {

    AccountResp addAccount(AccountReq accountReq);
    AccountResp getAccount(String accountNumber);

}
