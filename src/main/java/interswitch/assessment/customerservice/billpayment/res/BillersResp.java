package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BillersResp {
    private String status;
    private String message;
    private List<BillersData> data;
}
