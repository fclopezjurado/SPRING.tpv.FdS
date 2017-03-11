package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void createVoucher(@RequestBody VoucherWrapper voucherWrapper) {
    }

}
