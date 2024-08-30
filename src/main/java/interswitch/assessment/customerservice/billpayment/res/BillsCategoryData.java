package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BillsCategoryData {
    private Long id;
    private String name;
    private String code;
    private String description;
    private String country_code;
}
