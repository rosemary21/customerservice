package interswitch.assessment.customerservice.billpayment.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import interswitch.assessment.customerservice.account.model.Account;
import interswitch.assessment.customerservice.account.repository.AccountRepository;
import interswitch.assessment.customerservice.billpayment.dto.Utility;
import interswitch.assessment.customerservice.billpayment.model.BillPaymentTransaction;
import interswitch.assessment.customerservice.billpayment.req.CreateBillReq;
import interswitch.assessment.customerservice.billpayment.res.*;
import interswitch.assessment.customerservice.billpayment.service.BillPaymentService;
import interswitch.assessment.customerservice.billpayment.service.TransactionService;
import interswitch.assessment.customerservice.login.dto.CustomUserDetails;
import interswitch.assessment.customerservice.urlexecutor.HttpCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BillPaymentServiceimpl implements BillPaymentService {

    @Value("${billpayment.base.url}")
    private String billPaymentBaseUrl;

    @Value("${billpayment.category.url}")
    private String billPaymentCategoryUrl;

    @Value("${billpayment.validare.url}")
    private String billPaymentValidateUrl;

    @Value("${billpayment.billers.url}")
    private String billPaymentBillerUrl;

    @Value("${billpayment.billercode.url}")
    private String billPaymentBillerCodeUrl;

    @Value("${billpayment.create.billers.url}")
    private String billPaymentCreateBillerUrl;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    TransactionService transferService;

    @Autowired
     HttpCallService httpCallService;

    @Override
    public BillsCategoryResp getBillCategories() {
        try{
            String url=billPaymentBaseUrl+billPaymentCategoryUrl;
            log.info("Retreiving the url {}",url);
            String payload=httpCallService.doBasicGetFlutterWave(url);
            BillsCategoryResp resp= mapper.readValue(payload,BillsCategoryResp.class);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @Override
    public BillersResp getBillers(String billers){
        try{
            String url=billPaymentBaseUrl+"/bills/"+billers+billPaymentBillerUrl;
            String payload=httpCallService.doBasicGetFlutterWave(url);
            BillersResp resp= mapper.readValue(payload,BillersResp.class);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public BillerCodeResp getBillerCode(String billerCode){
        try{
            String url=billPaymentBaseUrl+"/billers/"+billerCode+billPaymentBillerCodeUrl;
            String payload=httpCallService.doBasicGetFlutterWave(url);
            log.info(mapper.writeValueAsString(payload));
            BillerCodeResp resp= mapper.readValue(payload,BillerCodeResp.class);
            log.info("response data {}",mapper.writeValueAsString(resp));
            return resp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public ValidateResp getValidateBill(String billerCode, String customerId, String itemcode){
        try{
            String url=billPaymentBaseUrl+billPaymentValidateUrl+itemcode+"/validate?code="+billerCode+"&customer="+customerId;
            log.info("getting the url {}",url);
            String payload=httpCallService.doBasicGetFlutterWave(url);
            ValidateResp resp= mapper.readValue(payload,ValidateResp.class);
            return resp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @Override
    public CreateBillResp createBiller(CreateBillReq req){
        try{
            String url=billPaymentBaseUrl+billPaymentCreateBillerUrl+req.getBillerCode()+"/items/"+req.getItemCode()+"/payment";
            req.setReference(Utility.generateAlphaNumbericReference(Utility.generateReference(7),8)+"-"+Utility.generateNumbericReference(Utility.generateReference(7),4)+"-"+Utility.generateAlphaNumbericReference(Utility.generateReference(7),4)+"-"+Utility.generateAlphaNumbericReference(Utility.generateReference(7),4)+"-"+Utility.generateAlphaNumbericReference(Utility.generateReference(7),12));
            log.info("geting the payment url {}",url);
            String request=mapper.writeValueAsString(req);
            log.info("getting the payment payload {}",request);
            boolean result= checkBalance(req.getAmount());
            log.info("getting the result {}",result);
            if(!result){
                CreateBillResp req1=new CreateBillResp();
                req1.setStatus("is96");
                req1.setMessage("InSufficient Balance");
                return req1;
            }
            req.setStatus("Initiated");
            BillPaymentTransaction billPaymentTransaction=transferService.createRecord(req);
            BillPaymentTransaction billPaymentTransactions= transferService.createTransfer(billPaymentTransaction);
            boolean updateResult= updateBalance(req.getAmount());
            billPaymentTransactions.setStatus("Processing");
            BillPaymentTransaction billPaymentTransaction1=   transferService.updateTransfer(billPaymentTransactions);
            if(updateResult){
                String payload=httpCallService.doBasicPostFlutterWave(url,request);
                log.info("response after calling {}",payload);
                CreateBillResp resp= mapper.readValue(payload,CreateBillResp.class);
                log.info("Entering the response {}",mapper.writeValueAsString(resp));
                if(resp.getStatus().equalsIgnoreCase("failed") || resp.getStatus().equalsIgnoreCase("error")){
                    reverse(req.getAmount());
                    billPaymentTransactions.setStatus("Reversed");

                }else{
                    billPaymentTransaction1.setStatus(req.getStatus());

                }
                transferService.updateTransfer(billPaymentTransaction1);

                return resp;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }



    public  boolean reverse(BigDecimal amount){

        String userName=getCurrentuser();
        Account value= accountRepository.findByPhoneNumber(userName);
        if(value.getBalance()!=null){
            BigDecimal newAmount= value.getBalance().add(amount);
            value.setBalance(newAmount);
            accountRepository.save(value);
            return true;
        }

        return false;

    }

    public  boolean updateBalance(BigDecimal amount){

        String userName=getCurrentuser();
        Account value= accountRepository.findByPhoneNumber(userName);
        BigDecimal walletBalance=value.getBalance();
        if(walletBalance!=null){
            BigDecimal newAmount= walletBalance.subtract(amount);
            value.setBalance(newAmount);
            accountRepository.save(value);
            return true;
        }

        return true;

    }

    public  boolean checkBalance(BigDecimal amount){

        String userName=getCurrentuser();
        Account value= accountRepository.findByPhoneNumber(userName);
        BigDecimal walletBalance=value.getBalance();
        if(walletBalance!=null){
            if(walletBalance.compareTo(amount)<1){
                return false;
            }
        }
        return true;
    }

    public String getCurrentuser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            String username = ((CustomUserDetails)principal).getUsername();
            return  username;
        } else {
            return null;
        }
    }

}
