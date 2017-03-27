package daos.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import wrappers.CashierBalanceWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})

public class CashierBalanceDaoIT {

    @Autowired
    private CashierBalanceDao cashierBalanceDao;
    
    @Test
    public void testCreate() {
        assertEquals(4, cashierBalanceDao.count());
    }
    
    @Test
    public void testFindById() {
        assertNotNull(cashierBalanceDao.findOne(1));
    }
    
    @Test
    public void testFindByDay(){        
        SimpleDateFormat dateFormat = new SimpleDateFormat(CashierBalanceWrapper.dateFormat);
        Calendar cal = Calendar.getInstance();    
        try {
            cal.setTime(dateFormat.parse(dateFormat.format(cal.getTime())));
        } catch (ParseException e) {
            fail();
        }        
        cal.add(Calendar.DATE, -1);        
        assertNotNull(cashierBalanceDao.findOneByDay(cal));
    }

}
