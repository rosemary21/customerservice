package interswitch.assessment.customerservice.account.repository;

import interswitch.assessment.customerservice.account.dto.AccountDto;
import interswitch.assessment.customerservice.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {

    Account findByPhoneNumber(String phoneNumber);
    Account findByPhoneNumberOrEmailOrBvn(String phoneNumber, String email,String bvn);
    Account findByEmail(String email);
    Account findByAccountNumber(String accountNumber);
}
