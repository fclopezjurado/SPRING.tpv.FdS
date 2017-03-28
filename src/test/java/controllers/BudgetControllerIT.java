package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wrappers.BudgetDetailWrapper;
import wrappers.BudgetListWrapper;
import wrappers.BudgetWrapper;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
public class BudgetControllerIT {

    @Autowired
    private BudgetController budgetController;

    @Test
    public void testCreateBudget() {
        int budgets = budgetController.findAll().getBudgetList().size();
        budgetController.createBudget();
        
        assertEquals(budgetController.findAll().getBudgetList().size(), budgets + 1);
    }

    @Test
    public void testGetBudgetByReference() {
        BudgetWrapper budgetWrapper = budgetController.findAll().getBudgetList().get(0);

        BudgetDetailWrapper budget = budgetController.getBudgetByReference(budgetWrapper.getReference());
        assertTrue(budget.getReference().length() > 20);
    }

    @Test
    public void testGetTicketByReferenceCommitted() {
        BudgetListWrapper budgets = budgetController.findAll();
        
        budgetController.deleteBudget(budgets.getBudgetList().get(0).getReference());
        assertEquals(budgetController.findAll().getBudgetList().size(), budgets.getBudgetList().size() - 1);
    }
}
