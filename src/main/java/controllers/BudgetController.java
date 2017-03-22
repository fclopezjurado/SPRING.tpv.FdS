package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.BudgetDao;
import entities.core.Budget;
import wrappers.BudgetListWrapper;
import wrappers.BudgetWrapper;
import wrappers.UserWrapper;

@Controller
public class BudgetController {

    @Autowired
    BudgetDao budgetDao;
    
    @Autowired
    public void setBudgetDao(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }
    
    public BudgetListWrapper getAllBudgetsByUser(UserWrapper user) {
        return new BudgetListWrapper(budgetDao.findByUserMobile(user.getMobile()));
    }
    
    public BudgetWrapper getBudgetByReference(String reference) {
        return new BudgetWrapper(budgetDao.findByReference(reference));
    }
    
    public void deleteBudget(BudgetWrapper budgetWrapper) {
        Budget budget = budgetDao.findByReference(budgetWrapper.getReference());
        budgetDao.delete(budget);
    }
    
    public void updateBudget(BudgetWrapper budgetWrapper) {
        Budget budget = budgetDao.findByReference(budgetWrapper.getReference());
        budgetDao.save(budget);
    }
}
