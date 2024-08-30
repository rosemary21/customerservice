package interswitch.assessment.customerservice.billpayment.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties
public class BillsCategoryResp {
    private String status;
    private String message;
    List<BillsCategoryData> data;
}
