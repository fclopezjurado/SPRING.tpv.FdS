package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.BudgetDao;
import entities.core.Budget;
import entities.core.Shopping;
import wrappers.BudgetDetailWrapper;
import wrappers.BudgetListWrapper;

@Controller
public class BudgetController {

    @Autowired
    BudgetDao budgetDao;
    
    @Autowired
    public void setBudgetDao(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }
    
    public BudgetListWrapper findAll() {
        return new BudgetListWrapper(budgetDao.findAll());
    }
    
    public void createBudget() {
        Budget budget = new Budget();
        budgetDao.save(budget);
    }
    
    public BudgetDetailWrapper getBudgetByReference(String reference) {
        return new BudgetDetailWrapper(budgetDao.findByReference(reference));
    }
    
    public void deleteBudget(String reference) {
        Budget budget = budgetDao.findByReference(reference);
        budgetDao.delete(budget);
    }
    
    public void addProduct(String reference, Shopping shopping) {
        
    }
}
