package entities.core;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class CashierBalanceTest {

    @Test
    public void testCashierBalance() {
        CashierBalance cBalance = new CashierBalance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        assertEquals(dateFormat.format(Calendar.getInstance().getTime()), dateFormat.format(cBalance.getDay().getTime()));
    }

    @Test
    public void testCashierBalanceFieldsConstructor() {
        BigDecimal doscientos = new BigDecimal(200);
        BigDecimal quinientos = new BigDecimal(500);
        BigDecimal diez = new BigDecimal(10);
        CashierBalance cBalance = new CashierBalance(doscientos, quinientos, quinientos, diez, diez);
        assertEquals(doscientos, cBalance.getChange());
        assertEquals(quinientos, cBalance.getCash());
        assertEquals(quinientos, cBalance.getTotalTiketsMoney());
        assertEquals(diez, cBalance.getChecks());
        assertEquals(diez, cBalance.getDataphone());
    }

}
