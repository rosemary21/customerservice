package interswitch.assessment.customerservice.billpayment.service;

import interswitch.assessment.customerservice.billpayment.req.CreateBillReq;
import interswitch.assessment.customerservice.billpayment.res.*;

public interface BillPaymentService {

    BillsCategoryResp getBillCategories();
    BillersResp getBillers(String billers);
    BillerCodeResp getBillerCode(String billerCode);
    ValidateResp getValidateBill(String billerCode, String customerId, String itemCode);

    CreateBillResp createBiller(CreateBillReq req);

}
