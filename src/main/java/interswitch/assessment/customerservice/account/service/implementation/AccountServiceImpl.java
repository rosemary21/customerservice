package interswitch.assessment.customerservice.account.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import interswitch.assessment.customerservice.account.dto.AccountDto;
import interswitch.assessment.customerservice.account.model.Account;
import interswitch.assessment.customerservice.account.repository.AccountRepository;
import interswitch.assessment.customerservice.account.req.AccountReq;
import interswitch.assessment.customerservice.account.res.AccountResp;
import interswitch.assessment.customerservice.account.service.AccountService;
import interswitch.assessment.customerservice.bvn.dto.BvnDto;
import interswitch.assessment.customerservice.bvn.service.BvnDetailService;
import interswitch.assessment.customerservice.bvn.web.req.BvnDetailsReq;
import interswitch.assessment.customerservice.bvn.web.res.BvnDetailsResp;
import interswitch.assessment.customerservice.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BvnDetailService bvnDetailService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper mapper;


    @Override
    public AccountResp addAccount(AccountReq accountReq) {
        AccountResp accountResp=new AccountResp();
        Account account=  modelMapper.map(accountReq, Account.class);
        BvnDto bvnDetailsReq=new BvnDto();
        bvnDetailsReq.setBvn(accountReq.getBvn());
        bvnDetailsReq.setDob(accountReq.getDateOfBirth());
        boolean result=PasswordUtil.checkPassword(account.getPassword());

        Account details=accountRepository.findByPhoneNumberOrEmailOrBvn(accountReq.getPhoneNumber(),accountReq.getEmail(), accountReq.getBvn());
         if(details!=null){
             accountResp.setMessage(messageSource.getMessage("account.number.exist", null, Locale.ENGLISH));
             accountResp.setStatus("false");
             return accountResp;
         }

        if(!result){
            accountResp.setMessage(messageSource.getMessage("password.incorrect", null, Locale.ENGLISH));
            accountResp.setStatus("false");
            return accountResp;
        }
         BvnDetailsResp bvnDetailsResp= bvnDetailService.verifyBvn(bvnDetailsReq);
         if(bvnDetailsResp.getStatus().equalsIgnoreCase("false")){
             accountResp.setMessage(messageSource.getMessage("bvn.mismatch.code", null, Locale.ENGLISH));
             accountResp.setStatus("false");
             return accountResp;
         }
        account.setPassword(passwordEncoder.encode(accountReq.getPassword()));
        accountRepository.save(account);
        accountResp.setMessage(messageSource.getMessage("account.success.code", null, Locale.ENGLISH));
        accountResp.setStatus("true");
        return accountResp;
    }

    @Override
    public AccountResp getAccount(String accountNumber){
        try{
            AccountResp accountResp=new AccountResp();
            Account account=  accountRepository.findByAccountNumber(accountNumber);
            AccountDto accountDto=modelMapper.map(account,AccountDto.class);
            accountResp.setMessage(messageSource.getMessage("account.fetch.success", null, Locale.ENGLISH));
            accountResp.setStatus("true");
            accountResp.setAccountDetails(accountDto);
            return accountResp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

}
