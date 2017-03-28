package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.TotalVouchersWrapper;
import wrappers.VoucherWrapper;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
public class VoucherControllerIT {

    @Autowired
    VoucherController vouchercontroller;

    @Test
    public void testTotalActiveVouchers() {
        TotalVouchersWrapper totVouWrapper = vouchercontroller.GetTotal();
        assertEquals(new BigDecimal("21.30"), totVouWrapper.getTotal());

    }

    @Test
    public void testSearchVouchers() {
        VoucherWrapper vouWrapper = new VoucherWrapper();
        vouWrapper.setReference("all");
        assertEquals(7, vouchercontroller.searchVoucher(vouWrapper).size());

    }

}
