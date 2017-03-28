package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import entities.core.CashierBalance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.CashierBalanceWrapper;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})

public class CashierBalanceDaoIT {

    @Autowired
    private CashierBalanceDao cashierBalanceDao;
    
    @Test
    public void testCreate() {
        assertEquals(4, cashierBalanceDao.count());
    }
    
    @Test
    public void testFindById() {
        CashierBalance balance = cashierBalanceDao.findOne(1);
        assertEquals(1, balance.getId());
        assertEquals(new BigDecimal(50).stripTrailingZeros(), balance.getChange().stripTrailingZeros());
        assertEquals(new BigDecimal(50).stripTrailingZeros(), balance.getTotalTiketsMoney().stripTrailingZeros());
        assertEquals(new BigDecimal(50).stripTrailingZeros(), balance.getCash().stripTrailingZeros());
        assertEquals(new BigDecimal(50).stripTrailingZeros(), balance.getChecks().stripTrailingZeros());
        assertEquals(new BigDecimal(50).stripTrailingZeros(), balance.getDataphone().stripTrailingZeros());  
        assertEquals(new BigDecimal(0).stripTrailingZeros(), balance.getBalance().stripTrailingZeros());  
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
        assertEquals(cal, cashierBalanceDao.findOneByDay(cal).getDay());
    }

}
