package api;

import api.exceptions.AlreadyExistCashierBalanceDayException;
import api.exceptions.NotFoundCashierBalanceIdException;
import org.junit.Before;
import org.junit.Test;
import wrappers.CashierBalanceWrapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CashierBalanceResourceTest {

    private CashierBalanceResource cashierBalanceResource;

    @Before
    public void before() {
        cashierBalanceResource = new CashierBalanceResource();
        cashierBalanceResource.setCashierBalanceController(new CashierBalanceControllerMock());
    }

    @Test
    public void testGetAllCashierBalances() {
        assertEquals(3, cashierBalanceResource.getAllCashierBalances().size());
    }

    @Test
    public void testGetCashierBalance() {
        assertEquals(
                new CashierBalanceWrapper(1, 50, 50, 50, 50, new SimpleDateFormat(CashierBalanceWrapper.dateFormat).format(new Date())),
                cashierBalanceResource.getCashierBalance(1));
    }

    @Test
    public void testCreateCashierBalance() {
        try {
            cashierBalanceResource.createCashierBalance(new CashierBalanceWrapper(50, 50, 50, 50, "03-03-1970"));
        } catch (AlreadyExistCashierBalanceDayException | ParseException e) {
            fail();
        } 
    }

    @Test
    public void testCreateCashierBalanceException() {
        try {
            cashierBalanceResource.createCashierBalance(
                    new CashierBalanceWrapper(50, 50, 50, 50, new SimpleDateFormat(CashierBalanceWrapper.dateFormat).format(new Date())));
            fail();
        } catch (AlreadyExistCashierBalanceDayException | ParseException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void testUpdateCashierBalance() {
        CashierBalanceWrapper balance = cashierBalanceResource.getCashierBalance(1);
        balance.setChange(new BigDecimal(80));
        try {
            cashierBalanceResource.updateCashierBalance(balance);
            assertEquals(balance.getChange(), cashierBalanceResource.getCashierBalance(1).getChange());
        } catch (NotFoundCashierBalanceIdException e) {
            fail();
        }

    }
}
