package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillersData {

    private Long id;
    private String name;
    private String logo;
    private String description;
    private String short_name;
    private String biller_code;
    private String country_code;
}
