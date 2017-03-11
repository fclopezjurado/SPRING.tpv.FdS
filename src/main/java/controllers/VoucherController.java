package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.VoucherDao;
import entities.core.Voucher;
import wrappers.TotalVouchersWrapper;
import wrappers.VoucherWrapper;

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

    public VoucherWrapper createVoucher(VoucherWrapper voucherWrapper) {
        Voucher voucherEntity = new Voucher(voucherWrapper.getValue(), voucherWrapper.getExpiration());
        voucherDao.save(voucherEntity);
        VoucherWrapper voucherCrerado = new VoucherWrapper();
        voucherCrerado.setReference(voucherEntity.getReference());
        return voucherCrerado;
    }

}
