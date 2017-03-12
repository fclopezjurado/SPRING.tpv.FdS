package api;

import static org.junit.Assert.*;

import java.math.BigDecimal;

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

        VoucherWrapper consumedvoucherWrapper = new RestBuilder<VoucherWrapper>(RestService.URL).path(Uris.VOUCHERS).path("/"+voucherWrapper.getReference()).clazz(VoucherWrapper.class)
                .post().build();        
                
        assertEquals(new BigDecimal("101.00"), consumedvoucherWrapper.getValue());
    }    
    
    @After
    public void after() {
        new RestService().deleteAll();
    }

}
