package interswitch.assessment.customerservice.account.controller;


import interswitch.assessment.customerservice.account.req.AccountReq;
import interswitch.assessment.customerservice.account.res.AccountResp;
import interswitch.assessment.customerservice.account.service.AccountService;
import interswitch.assessment.customerservice.bvn.dto.BvnDto;
import interswitch.assessment.customerservice.bvn.web.req.BvnDetailsReq;
import interswitch.assessment.customerservice.bvn.web.res.BvnDetailsResp;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * THis service does with the creation of account and fetching of the account information
 */
@Slf4j
@RestController
@RequestMapping(value = {"/api/v1/account"}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {


    @Autowired
    AccountService accountService;

    @ApiOperation(value = "Add Account Service", response = AccountResp.class, notes = "This is use to Account Service")
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    private ResponseEntity<AccountResp> addAccount(@RequestBody AccountReq accountReq) {
        var bvnResp = accountService.addAccount(accountReq);
        return new ResponseEntity<AccountResp>(bvnResp, HttpStatus.OK);
    }



    @ApiOperation(value = "Fetch Account Service", response = AccountResp.class, notes = "This is use to Fetch Account Service")
    @RequestMapping(value = {"/fetch/{phoneNumber}"}, method = RequestMethod.GET)
    private ResponseEntity<AccountResp>getAccount(@PathVariable String phoneNumber) {
        var bvnResp = accountService.getAccount(phoneNumber);
        return new ResponseEntity<AccountResp>(bvnResp, HttpStatus.OK);
    }

}
