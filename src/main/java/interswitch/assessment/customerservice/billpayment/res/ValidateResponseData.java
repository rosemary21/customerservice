package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateResponseData {

    private String response_code;
    private String address;
    private String response_message;
    private String name;
    private String biller_code;
    private String customer;
    private String product_code;
    private String email;
    private int fee;
    private int maximum;
    private int minimum;
}
