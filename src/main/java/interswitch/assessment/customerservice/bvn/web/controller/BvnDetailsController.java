package interswitch.assessment.customerservice.bvn.web.controller;

import interswitch.assessment.customerservice.bvn.dto.BvnDto;
import interswitch.assessment.customerservice.bvn.service.BvnDetailService;
import interswitch.assessment.customerservice.bvn.web.req.BvnDetailsReq;
import interswitch.assessment.customerservice.bvn.web.res.BvnDetailsResp;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = {"/api/v1/bvn"}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class BvnDetailsController {

    @Autowired
    private BvnDetailService bvnDetailService;

    /**
     * This service is use to get the bvn details after the bvn has been inputed.
     *
     * @param bvn
     * @return
     */

    @ApiOperation(value = "BVN Fetch service", response = BvnDetailsResp.class, notes = "This is use to Fetch BVN")
    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<BvnDetailsResp> getBvn(@RequestBody BvnDetailsReq bvn) {
        var bvnResp = bvnDetailService.getBvnDetails(bvn);
        return new ResponseEntity<BvnDetailsResp>(bvnResp, HttpStatus.OK);
    }


    /**
     * THis service does with the verification of bvn by asking the user to enter the dateofbirth and 2fa has been done to
     * the phone number tied to the bvn
     * @param bvn
     * @return
     */

    @ApiOperation(value = "BVN Verify service", response = BvnDetailsResp.class, notes = "This is use to Verify BVN")
    @RequestMapping(value = {"/verify"}, method = RequestMethod.POST)
    private ResponseEntity<BvnDetailsResp> verifyBvn(@RequestBody BvnDto bvn) {
        var bvnResp = bvnDetailService.verifyBvn(bvn);
        return new ResponseEntity<BvnDetailsResp>(bvnResp, HttpStatus.OK);
    }




}
