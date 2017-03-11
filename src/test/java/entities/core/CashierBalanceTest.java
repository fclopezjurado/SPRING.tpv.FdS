package entities.core;

import static org.junit.Assert.*;

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
    public void testCashierBalanceFieldsConstructor(){
        CashierBalance cBalance = new CashierBalance(200, 500, 10, 10);
        assertEquals(200, cBalance.getChange(), 0);
        assertEquals(500, cBalance.getCash(), 0);
        assertEquals(10, cBalance.getChecks(), 0);
        assertEquals(10, cBalance.getDataphone(), 0);
    }

   

}
