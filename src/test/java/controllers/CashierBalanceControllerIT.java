package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import daos.core.CashierBalanceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.CashierBalanceWrapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
public class CashierBalanceControllerIT {
    
    private static final int VALID_CASHIER_BALANACE_ID = 1;
    private static final BigDecimal CHANGE_UPDATE = new BigDecimal(0);
    private static final String CASHIER_BALANCE_DATE = "01-01-1970";
    private static final int CASHIER_BALANCE_CREATE_ID = 5;
    
    @Autowired
    private CashierBalanceController cashierBalanceController;
    
    @Autowired
    private CashierBalanceDao cashierBalanceDao;
    
    @Test
    public void testGetAll() {        
        assertEquals(4, cashierBalanceController.getAll().size());
    }

    @Test
    public void testCreateCashierBalance() {        
        try {
            cashierBalanceController.createCashierBalance(new CashierBalanceWrapper(CASHIER_BALANCE_CREATE_ID, 50, 50, 50, 50, CASHIER_BALANCE_DATE));
            assertEquals(5, cashierBalanceController.getAll().size());  
            if(cashierBalanceDao.findOne(CASHIER_BALANCE_CREATE_ID) != null){
                cashierBalanceDao.delete(CASHIER_BALANCE_CREATE_ID);
            }
        } catch (ParseException e) {
           fail();
        }              
    }

    @Test
    public void testGetCashierBalanceById() {
        assertEquals(1, cashierBalanceController.getCashierBalanceById(VALID_CASHIER_BALANACE_ID).getId());
    }

    @Test
    public void testExistCashierBalanceByDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CashierBalanceWrapper.dateFormat);
        assertEquals(false, cashierBalanceController.existCashierBalanceByDate(dateFormat.format(Calendar.getInstance().getTime())));
    }

    @Test
    public void testExistCashierBalanceId() {
        assertEquals(true, cashierBalanceController.existCashierBalanceId(VALID_CASHIER_BALANACE_ID));
    }

    @Test
    public void testUpdateCashierBalance() {
        CashierBalanceWrapper balanceWrapper = cashierBalanceController.getCashierBalanceById(VALID_CASHIER_BALANACE_ID);
        assertNotEquals(CHANGE_UPDATE, balanceWrapper.getChange());
        balanceWrapper.setChange(CHANGE_UPDATE);
        cashierBalanceController.updateCashierBalance(balanceWrapper);
        assertEquals(CHANGE_UPDATE.stripTrailingZeros(), cashierBalanceController.getCashierBalanceById(VALID_CASHIER_BALANACE_ID).getChange().stripTrailingZeros());        
    }

}
