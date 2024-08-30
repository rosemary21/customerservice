package interswitch.assessment.customerservice.bvn.service;

import interswitch.assessment.customerservice.bvn.dto.BvnDto;
import interswitch.assessment.customerservice.bvn.web.req.BvnDetailsReq;
import interswitch.assessment.customerservice.bvn.web.res.BvnDetailsResp;

public interface BvnDetailService {


    BvnDetailsResp getBvnDetails(BvnDetailsReq bdReq);


    BvnDetailsResp verifyBvn(BvnDto bvnDto);
}
