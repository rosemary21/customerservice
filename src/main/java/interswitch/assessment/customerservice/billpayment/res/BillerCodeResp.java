package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BillerCodeResp {

    private String status;
    private String message;
    private List<BillerCodeData> data;
}

