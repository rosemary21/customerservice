package interswitch.assessment.customerservice.transfer.repository;

import interswitch.assessment.customerservice.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository  extends JpaRepository<Transfer,Long> {
}
