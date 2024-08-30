package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateBillResp {
    private String status;
    private String message;
    private CreateBillRespData data;
}
