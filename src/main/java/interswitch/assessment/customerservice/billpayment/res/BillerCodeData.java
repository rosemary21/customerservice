package interswitch.assessment.customerservice.billpayment.res;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class BillerCodeData {

    private Long id;
    private String biller_code;
    private String name;
    private int default_commission;
    private String date_added;
    private String country;
    private boolean is_airtime;
    private String biller_name;
    private String item_code;
    private String short_name;
    private int fee;
    private boolean commission_on_fee;
    private String reg_expression;
    private String label_name;
    private BigDecimal amount;
    private boolean is_resolvable;
    private String group_name;
    private String category_name;
    private String is_data;
    private String default_commission_on_amount;
    private String commission_on_fee_or_amount;
    private String validity_period;
}
