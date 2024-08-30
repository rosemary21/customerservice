package interswitch.assessment.customerservice.billpayment.service.implementation;

import interswitch.assessment.customerservice.billpayment.dto.TransferResponseDto;
import interswitch.assessment.customerservice.billpayment.model.BillPaymentTransaction;
import interswitch.assessment.customerservice.billpayment.repository.BillPaymentTranRepository;
import interswitch.assessment.customerservice.billpayment.req.CreateBillReq;
import interswitch.assessment.customerservice.billpayment.service.TransactionService;
import interswitch.assessment.customerservice.login.resp.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    BillPaymentTranRepository billPaymentTranRepository;

    @Override
    public BillPaymentTransaction createTransfer(BillPaymentTransaction transaction){
        TransferResponseDto transferResponseDto=new TransferResponseDto();
        ResponseDto responseDto=new ResponseDto();
        BillPaymentTransaction v= billPaymentTranRepository.save(transaction);
        responseDto.setCode(messageSource.getMessage("service.success.code", null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("transaction.success", null, Locale.ENGLISH));
        transferResponseDto.setResponseDto(responseDto);
        return v;
    }

    @Override
    public BillPaymentTransaction updateTransfer(BillPaymentTransaction transaction){
        TransferResponseDto transferResponseDto=new TransferResponseDto();
        ResponseDto responseDto=new ResponseDto();
        log.info("success status {}",transaction.getStatus());
        BillPaymentTransaction billPaymentTransaction= billPaymentTranRepository.save(transaction);
        responseDto.setCode(messageSource.getMessage("service.success.code", null, Locale.ENGLISH));
        responseDto.setMessage(messageSource.getMessage("transaction.success", null, Locale.ENGLISH));
        transferResponseDto.setResponseDto(responseDto);
        return billPaymentTransaction;
    }


    @Override
    public BillPaymentTransaction createRecord(CreateBillReq createBillReq){
        BillPaymentTransaction transaction=new BillPaymentTransaction();
        transaction.setAmount(createBillReq.getAmount());
        transaction.setStatus(createBillReq.getStatus());
        transaction.setCustomerId(createBillReq.getCustomer_id());
        transaction.setType(createBillReq.getPaymentType());
        transaction.setNarration(createBillReq.getNarration());
        transaction.setFlutterwaveResponse(createBillReq.getFlutterwaveResponse());
        return transaction;

    }

}
