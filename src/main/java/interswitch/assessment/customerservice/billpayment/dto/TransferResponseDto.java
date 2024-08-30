package interswitch.assessment.customerservice.billpayment.dto;

import interswitch.assessment.customerservice.login.resp.ResponseDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransferResponseDto {
    private ResponseDto responseDto;
    private String status;
    private String message;
    private Meta meta;
}
