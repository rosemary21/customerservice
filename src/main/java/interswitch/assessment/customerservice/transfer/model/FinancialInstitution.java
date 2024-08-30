package interswitch.assessment.customerservice.transfer.model;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class FinancialInstitution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull
    private String institutionName;
    @Column(name = "institution_code", columnDefinition = "VARCHAR(8)", nullable = false)
    private String institutionCode;
    private String institutionLogo;
    private Integer category;
    private boolean internalAccount;
    @NotNull
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDate modifiedAt;
    private Long modifiedBy;

}
