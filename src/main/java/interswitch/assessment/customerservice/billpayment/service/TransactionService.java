package interswitch.assessment.customerservice.billpayment.service;

import interswitch.assessment.customerservice.billpayment.model.BillPaymentTransaction;
import interswitch.assessment.customerservice.billpayment.req.CreateBillReq;

public interface TransactionService {

    BillPaymentTransaction createTransfer(BillPaymentTransaction transaction);
    BillPaymentTransaction updateTransfer(BillPaymentTransaction transaction);
    BillPaymentTransaction createRecord(CreateBillReq createBillReq);
}
