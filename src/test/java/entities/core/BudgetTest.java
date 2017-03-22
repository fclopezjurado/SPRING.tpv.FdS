package entities.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BudgetTest {

    @Test
    public void testBudget() {
        Budget budget = new Budget(666);
        assertTrue(budget.getReference().length() > 20);
    }

}
