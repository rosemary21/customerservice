package interswitch.assessment.customerservice.billpayment.controller;

import interswitch.assessment.customerservice.billpayment.req.CreateBillReq;
import interswitch.assessment.customerservice.billpayment.res.*;
import interswitch.assessment.customerservice.billpayment.service.BillPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/billpayment", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BillPaymentController {

    @Autowired
    BillPaymentService billPaymentService;
    @GetMapping("/bill-categories")
    public BillsCategoryResp getAllCategories(){
        return billPaymentService.getBillCategories();
    }

    @GetMapping("/billers/{biller}")
    public BillersResp getBillers(@PathVariable String biller){
        return billPaymentService.getBillers(biller);
    }

    @GetMapping("/billercode/{billercode}")
    public BillerCodeResp getBillerCode(@PathVariable String billercode){
        return billPaymentService.getBillerCode(billercode);
    }

    @GetMapping("/validate/{billercode}/{customerid}/{itemcode}")
    public ValidateResp getBillerCode(@PathVariable String billercode, @PathVariable String customerid, @PathVariable String itemcode){
        return billPaymentService.getValidateBill(billercode,customerid,itemcode);
    }


    @PostMapping("/create")
    public ResponseEntity<CreateBillResp> getBillerCode(@RequestBody CreateBillReq createBillReq){

        CreateBillResp paymentStatusResp= billPaymentService.createBiller(createBillReq);

        if(paymentStatusResp.getStatus().equalsIgnoreCase("dk96")){

            return new ResponseEntity<>(paymentStatusResp, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(paymentStatusResp, HttpStatus.OK);

    }


}
