package interswitch.assessment.customerservice.login.service.implementation;

import interswitch.assessment.customerservice.account.model.Account;
import interswitch.assessment.customerservice.account.repository.AccountRepository;
import interswitch.assessment.customerservice.login.dto.CustomUserDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CustomerUserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    AccountRepository customerRepository;

    @Autowired
    HttpServletRequest request;

    @Override
    public CustomUserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {


        Account user=customerRepository.findByPhoneNumber(phoneNumber);

        log.info("getting the phone number information {}",phoneNumber);
        if(user == null){
            throw new UsernameNotFoundException("No user found with phone number: "+ phoneNumber + ". If user exists, contact administrator as account might have been disabled");
        }
        CustomUserDetails customUserDetails=new CustomUserDetails(user.getPhoneNumber(), user.getPassword(), new ArrayList<>());
        return customUserDetails;
    }


}
