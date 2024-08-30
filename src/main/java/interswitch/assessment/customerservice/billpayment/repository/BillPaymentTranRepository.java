package interswitch.assessment.customerservice.billpayment.repository;

import interswitch.assessment.customerservice.billpayment.model.BillPaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillPaymentTranRepository extends JpaRepository<BillPaymentTransaction,Long> {
}
