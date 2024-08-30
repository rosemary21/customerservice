package interswitch.assessment.customerservice.transfer.service.implementation;

import interswitch.assessment.customerservice.login.resp.ResponseDto;
import interswitch.assessment.customerservice.transfer.dto.FinancialInstitutionResp;
import interswitch.assessment.customerservice.transfer.dto.InstitutionDto;
import interswitch.assessment.customerservice.transfer.dto.TransferDto;
import interswitch.assessment.customerservice.transfer.model.FinancialInstitution;
import interswitch.assessment.customerservice.transfer.model.Transfer;
import interswitch.assessment.customerservice.transfer.repository.FinancialInstitutionRepository;
import interswitch.assessment.customerservice.transfer.repository.TransferRepository;
import interswitch.assessment.customerservice.transfer.res.PageRequests;
import interswitch.assessment.customerservice.transfer.res.TransferResponse;
import interswitch.assessment.customerservice.transfer.service.TransferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    ModelMapper mapper;
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    FinancialInstitutionRepository financialInstitutionRepository;

    @Override
    public TransferResponse createTransfer(TransferDto dto) {
        TransferResponse transferResponse=new TransferResponse();
        ResponseDto responseDto=new ResponseDto();
        Transfer transfer=   mapper.map(dto, Transfer.class);
        transferRepository.save(transfer);
        responseDto.setMessage(messageSource.getMessage("transfer.success.code", null, Locale.ENGLISH));
        responseDto.setCode("is00");
        transferResponse.setResponseDto(responseDto);
        return transferResponse;

    }


    @Override
    public TransferResponse createTransferUSSD( Map<String, String> allParams) {

        String nameEnquiryRef = allParams.get("nameEnquiryRef");
        String destinationInstitutionCode = allParams.get("destinationInstitutionCode");
        String channelCode = allParams.get("channelCode");
        String beneficiaryAccountName = allParams.get("beneficiaryAccountName");
        String beneficiaryAccountNumber = allParams.get("beneficiaryAccountNumber");
        String beneficiaryBankVerificationNumber = allParams.get("beneficiaryBankVerificationNumber");
        String beneficiaryKYCLevel = allParams.get("beneficiaryKYCLevel");
        String originatorAccountName = allParams.get("originatorAccountName");
        String originatorBankVerificationNumber = allParams.get("originatorBankVerificationNumber");
        String originatorKYCLevel = allParams.get("originatorKYCLevel");
        String transactionLocation = allParams.get("transactionLocation");
        String amount = allParams.get("amount");
        String narration = allParams.get("narration");
        String account_id = allParams.get("account_id");
        TransferDto transferDto=new TransferDto();
        transferDto.setNameEnquiryRef(nameEnquiryRef);
        transferDto.setDestinationInstitutionCode(destinationInstitutionCode);
        transferDto.setChannelCode(channelCode);
        transferDto.setBeneficiaryAccountName(beneficiaryAccountName);
        transferDto.setBeneficiaryAccountNumber(beneficiaryAccountNumber);
        transferDto.setBeneficiaryBankVerificationNumber(beneficiaryBankVerificationNumber);
        transferDto.setBeneficiaryKYCLevel(beneficiaryKYCLevel);
        transferDto.setOriginatorAccountName(originatorAccountName);
        transferDto.setOriginatorBankVerificationNumber(originatorBankVerificationNumber);
        transferDto.setOriginatorKYCLevel(originatorKYCLevel);
        transferDto.setTransactionLocation(transactionLocation);
        BigDecimal bigDecimal=new BigDecimal(amount);
        transferDto.setAmount(bigDecimal);
        transferDto.setNarration(narration);
        TransferResponse transferResponse= createTransfer(transferDto);
        return transferResponse;
    }

    @Override
    public FinancialInstitutionResp getFinancialInstitution(PageRequests pageDescription) {

        FinancialInstitutionResp financialInstitutionResp=new FinancialInstitutionResp();
        Page<FinancialInstitution> financialInstitutions=  financialInstitutionRepository.findAll(PageRequest.of(pageDescription.getPageNo(), pageDescription.getPageSize()));
        List<InstitutionDto> institutionDtoList=convertFinancialInstutionEntityToDto(financialInstitutions.getContent());
        financialInstitutionResp.setInstitutionDtoList(institutionDtoList);
        return financialInstitutionResp;
    }

    public List<InstitutionDto> convertFinancialInstutionEntityToDto(List<FinancialInstitution> financialInstitutions){
        List<InstitutionDto> institutionDtoList=new ArrayList<>();
        for (FinancialInstitution financialInstitution: financialInstitutions){
           InstitutionDto institutionDto=mapper.map(financialInstitution,InstitutionDto.class);
           institutionDtoList.add(institutionDto);
        }
        return institutionDtoList;

    }
}
