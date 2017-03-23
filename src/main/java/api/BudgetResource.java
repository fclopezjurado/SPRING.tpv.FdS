package api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.BudgetController;
import wrappers.BudgetDetailWrapper;
import wrappers.BudgetListWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class BudgetResource {

    private BudgetController budgetController;

    @Autowired
    public void setUserController(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.POST)
    public void createBudget() {
        budgetController.createBudget();
    }

    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.GET)
    public BudgetListWrapper getBudgets() {
        return budgetController.findAll();
    }
    
    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.GET)
    public BudgetDetailWrapper getBudgetsByReference(@PathVariable String reference) {
        return budgetController.getBudgetByReference(reference);
    }

    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.DELETE)
    public void deleteBudget(@RequestBody String reference) {
        budgetController.deleteBudget(reference);
    }
    
    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.PUT)
    public void updateBudget(BudgetDetailWrapper budgetWrapper) {
        //BudgetController.updateBudget(budgetWrapper);
    }

}
