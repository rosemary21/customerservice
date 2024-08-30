package interswitch.assessment.customerservice.transfer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstitutionDto {

    private Long id;
    private String institutionName;
    private String institutionCode;
    private String institutionLogo;
    private String category;
    private boolean internalAccount;
    private Date createdAt;
    private Long createdBy;
    private Date modifiedAt;
    private Long modifiedBy;
}
