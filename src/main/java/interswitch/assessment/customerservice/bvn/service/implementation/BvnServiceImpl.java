package interswitch.assessment.customerservice.bvn.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import interswitch.assessment.customerservice.bvn.dto.BvnDto;
import interswitch.assessment.customerservice.bvn.service.BvnDetailService;
import interswitch.assessment.customerservice.bvn.web.req.BvnDetailsReq;
import interswitch.assessment.customerservice.bvn.web.res.BvnDetailsResp;
import interswitch.assessment.customerservice.urlexecutor.HttpCallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
public class BvnServiceImpl implements BvnDetailService {

    @Autowired
    private ObjectMapper om;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private HttpCallService hcs;

    @Value("${idPass.base.url}")
    private String idPassBaseUrl;
    @Value("${idPass.bvn.url.path}")
    private String bvnUrlPath;

    @Override
    public BvnDetailsResp getBvnDetails(BvnDetailsReq bdReq) {
        try {
            var payload = om.writerWithDefaultPrettyPrinter().writeValueAsString(bdReq);
            var resp = hcs.idVerifyPassUrlCall(idPassBaseUrl+bvnUrlPath,payload);
            log.info("response on bvn response {}",resp);
            if(!resp.equalsIgnoreCase("No_response_from_idVerificationPass"))
                return om.readValue(resp,BvnDetailsResp.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BvnDetailsResp verifyBvn(BvnDto bvnDto) {
        BvnDetailsResp resp=new BvnDetailsResp();
        BvnDetailsReq bvnDetailsReq=new BvnDetailsReq();
        bvnDetailsReq.setNumber(bvnDto.getBvn());
        BvnDetailsResp bvnDetailsRep=getBvnDetails(bvnDetailsReq);
        if(!bvnDetailsRep.getData().getDateOfBirth().equalsIgnoreCase(bvnDto.getDob())){
            resp.setMessage(messageSource.getMessage("bvn.mismatch.code", null, Locale.ENGLISH));
            resp.setStatus("false");
            return resp;
        }

        resp.setMessage(messageSource.getMessage("bvn.verify.success", null, Locale.ENGLISH));
        resp.setStatus("true");
        resp.setResponseCode("00");
        return resp;

    }
}
