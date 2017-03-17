package api;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.TotalVouchersWrapper;
import wrappers.VoucherWrapper;

public class VoucherResourceFunctionalTesting {

    @Before
    public void init() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void GetTotal() {
        TotalVouchersWrapper totalVouchersWrapper = new RestBuilder<TotalVouchersWrapper>(RestService.URL).path(Uris.VOUCHERS)
                .clazz(TotalVouchersWrapper.class).get().build();
        assertEquals(new BigDecimal("21.30"), totalVouchersWrapper.getTotal());
    }

    @Test
    public void createVoucher() {
        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("100"));
        VoucherWrapper voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        assertNotNull(voucherWrapper.getReference());
    }

    @Test
    public void consumeVoucher() {
        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("101.00"));
        VoucherWrapper voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        VoucherWrapper consumedvoucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS)
                .path("/" + voucherWrapper.getReference()).clazz(VoucherWrapper.class).post().build();

        assertEquals(new BigDecimal("101.00"), consumedvoucherWrapper.getValue());
    }

    @Test
    public void searchVoucher() {

        new RestService().deleteAll();

        VoucherWrapper newVoucherWrapper = new VoucherWrapper();
        newVoucherWrapper.setValue(new BigDecimal("133.00"));
        VoucherWrapper voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        List<VoucherWrapper> VoucherList = Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS)
                .path("/" + voucherWrapper.getReference()).clazz(VoucherWrapper[].class).get().build());

        assertEquals(1, VoucherList.size());

        newVoucherWrapper.setValue(new BigDecimal("134.00"));
        voucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).clazz(VoucherWrapper.class)
                .body(newVoucherWrapper).post().build();

        VoucherList = Arrays.asList(new RestBuilder<VoucherWrapper[]>(RestService.URL).path(Uris.VOUCHERS).path("/all")
                .clazz(VoucherWrapper[].class).get().build());

        assertEquals(2, VoucherList.size());

    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
