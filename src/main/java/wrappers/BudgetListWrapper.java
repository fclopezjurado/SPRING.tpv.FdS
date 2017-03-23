package wrappers;

import java.util.ArrayList;
import java.util.List;

import entities.core.Budget;

public class BudgetListWrapper {

    List<BudgetWrapper> budgetList;
    
    public BudgetListWrapper(List<Budget> budgets) {
        budgetList = new ArrayList<BudgetWrapper>();
        
        for (Budget budget : budgets){
            budgetList.add(new BudgetWrapper(budget));
        }
    }

    @Override
    public String toString() {
        return "BudgetListWrapper [budgetList=" + budgetList + "]";
    }

}
