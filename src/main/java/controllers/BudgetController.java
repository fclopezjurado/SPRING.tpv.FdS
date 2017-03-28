package controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.BudgetDao;
import daos.core.ProductDao;
import entities.core.Budget;
import entities.core.Product;
import entities.core.Shopping;
import wrappers.BudgetDetailWrapper;
import wrappers.BudgetListWrapper;

@Controller
public class BudgetController {

    @Autowired
    BudgetDao budgetDao;
    
    @Autowired
    ProductDao productDao;
    
    @Autowired
    public void setBudgetDao(BudgetDao budgetDao) {
        this.budgetDao = budgetDao;
    }
    
    public BudgetListWrapper findAll() {
        return new BudgetListWrapper(budgetDao.findAll());
    }
    
    public void createBudget() {
        Budget budget = new Budget(System.currentTimeMillis());
        budgetDao.save(budget);
    }
    
    public BudgetDetailWrapper getBudgetByReference(String reference) {
        return new BudgetDetailWrapper(budgetDao.findByReference(reference));
    }
    
    public void deleteBudget(String reference) {
        Budget budget = budgetDao.findByReference(reference);
        budgetDao.delete(budget);
    }

    public void addProductToBudget(String reference, long id, int amount) {
        Budget budget = budgetDao.findByReference(reference);
        List<Product> products = productDao.findAll();
        
        Product found = null;
        
        for (Product product : products) {
            if (product.getId() == id) {
                found = product;
                break;
            }
        }
        if (found != null){
            budget.addShopping(new Shopping(amount, 0, found.getId(), found.getDescription(), found.getRetailPrice()));
        }
        
        budgetDao.save(budget);
    }
}
