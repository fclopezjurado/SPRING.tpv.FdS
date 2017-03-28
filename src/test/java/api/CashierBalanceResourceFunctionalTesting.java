package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

public class CashierBalanceResourceFunctionalTesting {

    private String token;

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
        token = new RestService().loginAdmin();
    }

    @Test
    public void testCreateCashierBalance() {
        CashierBalanceWrapper wrapper = new CashierBalanceWrapper(50, 50, 50, 50, "03-03-1970");

        assertNotNull(new RestBuilder<CashierBalanceWrapper>(RestService.URL).path(Uris.CASHIER_BALANCE).clazz(CashierBalanceWrapper.class)
                .body(wrapper).basicAuth(token, "").post().build());
    }

    @Test
    public void testGetAllCashierBalances() {
        CashierBalancesListWrapper wrapper = new RestBuilder<CashierBalancesListWrapper>(RestService.URL).path(Uris.CASHIER_BALANCE)
                .clazz(CashierBalancesListWrapper.class).basicAuth(token, "").get().build();
        assertEquals(4, wrapper.size());
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }
}
