package api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.TotalVouchersWrapper;

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

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
