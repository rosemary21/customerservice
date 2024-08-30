package interswitch.assessment.customerservice.transfer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import interswitch.assessment.customerservice.transfer.enums.ChannelType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class TransferDto {

    private String nameEnquiryRef;
    @NotNull(message = "Amount is a required field")
    private BigDecimal amount;
    @NotNull(message = "DestinationInstitutionCode is a required field")
    @Size(max = 6, message = "Destination code cannot be greater than 6")
    private String destinationInstitutionCode;
    @NotEmpty(message = "BeneficiaryAccountName is a required field")
    @Size(max = 100)
    private String beneficiaryAccountName;
    @NotEmpty(message = "BeneficiaryAccountNumber is a required field")
    @Size(max = 20)
    private String beneficiaryAccountNumber;
    @Size(max = 20)
    private String beneficiaryBankVerificationNumber;
    @Size(max = 100)
    @NotEmpty(message = "OriginatorAccountName field is required")
    private String originatorAccountName;
    @Size(max = 20)
    @NotEmpty(message = "OriginatorAccountNumber field is required")
    private String originatorAccountNumber;
    @Size(max = 20)
    @NotEmpty(message = "originatorBankVerificationNumber field is required")
    private String originatorBankVerificationNumber;
    private String originatorWalletId;
    @Size(max = 100, message = "LENGTH OF NARRATION IS MAX 100")
    private String narration;
    private String paymentReference;
    private String transactionLocation;
    private String originatorKYCLevel;
    @NotEmpty(message = "ChannelCode is a required field")
    @Max(value = 12, message = "ChannelCode is too large")
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

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;



}
