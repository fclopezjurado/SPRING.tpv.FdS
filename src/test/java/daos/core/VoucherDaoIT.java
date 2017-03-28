package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class VoucherDaoIT {

    @Autowired
    private VoucherDao voucherDao;

    @Test
    public void testCreate() {
        assertEquals(7, voucherDao.count());
    }

    @Test
    public void testTotalActive() {
        assertEquals(new BigDecimal("21.30"), voucherDao.findTotalActiveVouchers());
    }

}
