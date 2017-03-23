package api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.BudgetController;
import wrappers.BudgetListWrapper;
import wrappers.BudgetWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class BudgetResource {

    private BudgetController budgetController;

    @Autowired
    public void setUserController(BudgetController budgetController) {
        this.budgetController = budgetController;
    }

    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.POST)
    public void createBudget(@RequestBody BudgetWrapper budgetWrapper) {
        //budgetController.createBudget(budgetWrapper);
    }

    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.GET)
    public BudgetListWrapper getBudgets() {
        return budgetController.findAll();
    }

    @RequestMapping(value = Uris.BUDGETS, method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody String reference) {
        budgetController.deleteBudget(reference);
    }

}
