package interswitch.assessment.customerservice.billpayment.model;

import interswitch.assessment.customerservice.account.model.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;


@Entity
@Data
public class BillPaymentTransaction extends AbstractEntity {

    private String customerId;
    private String country;
    private String customer;
    private String recurrence;
    private BigDecimal amount;
    private String type;
    private String reference;
    private String billerName;
    private String billerType;
    private String flwRef;
    private String token;
    private String touchgoldRef;
    private String netWork;
    @Lob
    private String Response;
    private String status;
    private String narration;
    @Lob
    private String flutterwaveResponse;
}
