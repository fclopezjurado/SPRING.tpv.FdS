package controllers;

import java.util.ArrayList;
import java.util.List;

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

    public VoucherWrapper consumeVoucher(VoucherWrapper consumeVoucherWrapper) {

        Voucher voucherEntity = voucherDao.findFirstByReference(consumeVoucherWrapper.getReference());
        VoucherWrapper voucherConsumido = new VoucherWrapper();

        if (voucherEntity != null) {

            if (!voucherEntity.used() && !voucherEntity.expired()) {
                voucherEntity.consume();
                voucherDao.save(voucherEntity);
                voucherConsumido.setReference(voucherEntity.getReference());
                voucherConsumido.setValue(voucherEntity.getValue());
            }
        }
        return voucherConsumido;
    }

    public List<VoucherWrapper> searchVoucher(VoucherWrapper consumeVoucherWrapper) {

        List<VoucherWrapper> VoucherList = new ArrayList<>();

        if (consumeVoucherWrapper.getReference().equals("all")) {

            List<Voucher> vouchers = voucherDao.findAll();

            for (Voucher voucher : vouchers) {

                VoucherWrapper voucherConsultado = new VoucherWrapper();

                voucherConsultado.setReference(voucher.getReference());
                voucherConsultado.setValue(voucher.getValue());
                voucherConsultado.setCreated(voucher.getCreated());
                voucherConsultado.setDateOfUse(voucher.getDateOfUse());
                voucherConsultado.setExpiration(voucher.getDateOfExpiration());

                VoucherList.add(voucherConsultado);

            }

        } else {

            Voucher voucherEntity = voucherDao.findFirstByReference(consumeVoucherWrapper.getReference());
            VoucherWrapper voucherConsultado = new VoucherWrapper();

            if (voucherEntity != null) {
                voucherConsultado.setReference(voucherEntity.getReference());
                voucherConsultado.setValue(voucherEntity.getValue());
                voucherConsultado.setCreated(voucherEntity.getCreated());
                voucherConsultado.setDateOfUse(voucherEntity.getDateOfUse());
                voucherConsultado.setExpiration(voucherEntity.getDateOfExpiration());
            }

            VoucherList.add(voucherConsultado);

        }

        return VoucherList;
    }

}
