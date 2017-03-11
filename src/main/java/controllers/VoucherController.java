package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.VoucherDao;
import wrappers.TotalVouchersWrapper;

@Controller
public class VoucherController {

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    public void setVoucherDao(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }

    public TotalVouchersWrapper GetTotal() {
        return new TotalVouchersWrapper(this.voucherDao.findTotalActiveVouchers());
    }

}
