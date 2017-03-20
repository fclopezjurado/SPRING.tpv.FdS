package api;

import org.junit.Before;
import org.junit.Test;

import api.exceptions.AlreadyExistCashierBalanceDayException;
import api.exceptions.NotFoundCashierBalanceIdException;
import wrappers.CashierBalanceWrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        } catch (AlreadyExistCashierBalanceDayException e) {
            fail();
        }
    }

    @Test
    public void testCreateCashierBalanceException() {
        try {
            cashierBalanceResource.createCashierBalance(
                    new CashierBalanceWrapper(50, 50, 50, 50, new SimpleDateFormat(CashierBalanceWrapper.dateFormat).format(new Date())));
            fail();
        } catch (AlreadyExistCashierBalanceDayException e) {
            assertEquals(1, 1);
        }
    }

    @Test
    public void testUpdateCashierBalance() {
        CashierBalanceWrapper balance = cashierBalanceResource.getCashierBalance(1);
        balance.setChange(80);
        try {
            cashierBalanceResource.updateCashierBalance(balance);
            assertEquals(balance.getChange(), cashierBalanceResource.getCashierBalance(1).getChange(), 0);
        } catch (NotFoundCashierBalanceIdException e) {
            fail();
        }

    }
}
