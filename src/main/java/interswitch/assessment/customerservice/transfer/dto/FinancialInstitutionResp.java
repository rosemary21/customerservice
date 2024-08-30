package interswitch.assessment.customerservice.transfer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinancialInstitutionResp {
    private List<InstitutionDto> institutionDtoList;
}
