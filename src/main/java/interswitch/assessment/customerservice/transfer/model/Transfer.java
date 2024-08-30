package interswitch.assessment.customerservice.transfer.model;

import interswitch.assessment.customerservice.account.model.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transfer extends AbstractEntity {

    private String nameEnquiryRef;
    private BigDecimal amount;
    private String destinationInstitutionCode;
    private String beneficiaryAccountName;
    private String beneficiaryAccountNumber;
    private String beneficiaryBankVerificationNumber;
    private String originatorAccountName;
    private String originatorAccountNumber;
    private String originatorBankVerificationNumber;
    private String originatorWalletId;
    private String narration;
    private String paymentReference;
    private String transactionLocation;
    private String originatorKYCLevel;
    private String channelCode;
    private String beneficiaryKYCLevel;
    private String transactionId;
    private String commandId;
    private BigDecimal fee;
    private String pin;
    private String transactionType;
    private LocalDateTime createdAt;
    private String gelValue;
    private String paymentMethod;
    private String walletAccount;
}
