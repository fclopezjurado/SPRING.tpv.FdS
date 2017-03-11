package api;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.InvalidNewVoucherException;
import controllers.VoucherController;
import wrappers.TotalVouchersWrapper;
import wrappers.VoucherWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.VOUCHERS)

public class VouchersResource {

    private VoucherController voucherController;

    @Autowired
    public void setVoucherController(VoucherController voucherController) {
        this.voucherController = voucherController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public TotalVouchersWrapper total() {
        return voucherController.GetTotal();
    }

    @RequestMapping(method = RequestMethod.POST)
    public VoucherWrapper createVoucher(@RequestBody VoucherWrapper voucherWrapper) throws InvalidNewVoucherException {
        validNewVoucher(voucherWrapper);
        return voucherController.createVoucher(voucherWrapper);
    }

    private void validNewVoucher(VoucherWrapper voucherWrapper) throws InvalidNewVoucherException {
        if (voucherWrapper.getValue() == null || voucherWrapper.getValue().compareTo(BigDecimal.ZERO) < 1) {
            throw new InvalidNewVoucherException();
        }
    }

}
