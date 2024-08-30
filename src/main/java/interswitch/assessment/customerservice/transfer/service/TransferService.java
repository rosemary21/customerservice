package interswitch.assessment.customerservice.transfer.service;

import interswitch.assessment.customerservice.transfer.dto.FinancialInstitutionResp;
import interswitch.assessment.customerservice.transfer.dto.TransferDto;
import interswitch.assessment.customerservice.transfer.res.PageRequests;
import interswitch.assessment.customerservice.transfer.res.TransferResponse;

import java.util.Map;

public interface TransferService {

    TransferResponse createTransfer(TransferDto dto);
    TransferResponse createTransferUSSD(Map<String, String> allParams);

    FinancialInstitutionResp getFinancialInstitution( PageRequests pageDescription);

}
