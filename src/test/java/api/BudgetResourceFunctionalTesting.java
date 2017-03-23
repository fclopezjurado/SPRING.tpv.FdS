package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import wrappers.BudgetDetailWrapper;
import wrappers.BudgetListWrapper;
import static org.junit.Assert.*;

public class BudgetResourceFunctionalTesting {

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void testGetBudgets() {
        
        BudgetListWrapper budgets = new RestBuilder<BudgetListWrapper>(RestService.URL).path(Uris.BUDGETS).clazz(BudgetListWrapper.class).get()
                .build();
        assertTrue(budgets.getBudgetList().get(0).getReference().length() > 20);
    }
    
    @Test
    public void testGetBudgetByReference() {
        
        BudgetListWrapper budgets = new RestBuilder<BudgetListWrapper>(RestService.URL).path(Uris.BUDGETS).clazz(BudgetListWrapper.class).get()
                .build();
        
        BudgetDetailWrapper budget = new RestBuilder<BudgetDetailWrapper>(RestService.URL).path(Uris.BUDGETS).path("/" + budgets.getBudgetList().get(0).getReference()).clazz(BudgetDetailWrapper.class).get()
                .build();
        assertTrue(budget.getReference().length() > 20);
    }
    
    @Test
    public void testCreateBudget() {
        new RestBuilder<Object>(RestService.URL).path(Uris.BUDGETS).post()
                .build();
        
        BudgetListWrapper budgets = new RestBuilder<BudgetListWrapper>(RestService.URL).path(Uris.BUDGETS).clazz(BudgetListWrapper.class).get()
                .build();
        assertTrue(budgets.getBudgetList().size() > 3);
    }

    @After
    public void after() {
        //new RestService().deleteAll();
    }

}
