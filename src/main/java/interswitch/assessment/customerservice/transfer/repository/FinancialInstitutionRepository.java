package interswitch.assessment.customerservice.transfer.repository;

import interswitch.assessment.customerservice.transfer.model.FinancialInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialInstitutionRepository  extends JpaRepository<FinancialInstitution,Long> {
}
