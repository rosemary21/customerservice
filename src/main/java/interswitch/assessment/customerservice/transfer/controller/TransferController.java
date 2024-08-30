package interswitch.assessment.customerservice.transfer.controller;

import interswitch.assessment.customerservice.account.res.AccountResp;
import interswitch.assessment.customerservice.transfer.dto.FinancialInstitutionResp;
import interswitch.assessment.customerservice.transfer.dto.TransferDto;
import interswitch.assessment.customerservice.transfer.res.PageRequests;
import interswitch.assessment.customerservice.transfer.res.TransferResponse;
import interswitch.assessment.customerservice.transfer.service.TransferService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This is the servicd that has to do with transfer this create transfer request and fetch banks
 */

@Slf4j
@RestController
@RequestMapping(value = {"/api/v1/transfer"}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class TransferController {

    @Autowired
    TransferService transferService;


    @ApiOperation(value = "Transfer Account Service", response = AccountResp.class, notes = "This is use to Transfer Service")
    @RequestMapping(value = {"/request"}, method = RequestMethod.POST)
    private ResponseEntity<TransferResponse> transfer(@RequestBody TransferDto transferDto) {
        var transferResponse = transferService.createTransfer(transferDto);
        return new ResponseEntity<TransferResponse>(transferResponse, HttpStatus.OK);
    }


    @ApiOperation(value = "Transfer Account Service", response = AccountResp.class, notes = "This is use to Transfer Service")
    @RequestMapping(value = {"/request/ussd"}, method = RequestMethod.POST)
    private ResponseEntity<TransferResponse> addAccount(@RequestParam Map<String, String> allParams) {
        var transferResponse = transferService.createTransferUSSD(allParams);
        return new ResponseEntity<TransferResponse>(transferResponse, HttpStatus.OK);
    }


    @ApiOperation(value = "Transfer Account Service", response = AccountResp.class, notes = "This is use to Transfer Service")
    @RequestMapping(value = {"/fetch/banks"}, method = RequestMethod.POST)
    private ResponseEntity<FinancialInstitutionResp> fetchAccount(@RequestBody PageRequests transferDto) {
        var transferResponse = transferService.getFinancialInstitution(transferDto);
        return new ResponseEntity<FinancialInstitutionResp>(transferResponse, HttpStatus.OK);
    }



}
