package api;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.InvalidNewVoucherException;
import api.exceptions.InvalidVoucherReferenceException;
import api.exceptions.NotFoundReferenceVoucherException;
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

    @RequestMapping(value = Uris.ID, method = RequestMethod.POST)
    public VoucherWrapper consume(@PathVariable(value = "id") String referencia)
            throws InvalidVoucherReferenceException, NotFoundReferenceVoucherException {
        if (referencia.isEmpty() || referencia.length() < 27) {
            throw new InvalidVoucherReferenceException();
        }
        VoucherWrapper cosumeVoucherWrapper = new VoucherWrapper();
        cosumeVoucherWrapper.setReference(referencia);

        VoucherWrapper resulVoucherWrapper = voucherController.consumeVoucher(cosumeVoucherWrapper);
        if (resulVoucherWrapper.getReference() == null) {
            throw new NotFoundReferenceVoucherException();
        }

        return resulVoucherWrapper;
    }

}
