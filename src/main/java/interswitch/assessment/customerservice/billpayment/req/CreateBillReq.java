package interswitch.assessment.customerservice.billpayment.req;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateBillReq {

    private String country;
    private String customer_id;
    private String reference;
    private BigDecimal amount;
    private String callback_url;
    private String billerCode;
    private String itemCode;


    private String status;
    private String narration;
    private String paymentType;
    @Lob
    private String flutterwaveResponse;
}
