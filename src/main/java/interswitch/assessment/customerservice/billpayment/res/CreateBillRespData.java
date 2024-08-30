package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateBillRespData {

    private String phone_number;
    private BigDecimal amount;
    private String network;
    private String code;
    private String tx_ref;
    private String reference;
    private String batch_reference;
    private String recharge_token;
    private BigDecimal fee;
}
