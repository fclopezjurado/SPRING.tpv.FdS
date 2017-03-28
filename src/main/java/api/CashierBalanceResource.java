package api;

import api.exceptions.AlreadyExistCashierBalanceDayException;
import api.exceptions.NotFoundCashierBalanceIdException;
import controllers.CashierBalanceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import wrappers.CashierBalanceWrapper;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(Uris.VERSION + Uris.CASHIER_BALANCE)
public class CashierBalanceResource {

    private CashierBalanceController cashierBalanceController;

    @Autowired
    public void setCashierBalanceController(CashierBalanceController cashierBalanceController) {
        this.cashierBalanceController = cashierBalanceController;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public List<CashierBalanceWrapper> getAllCashierBalances() {
        return cashierBalanceController.getAll();
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public CashierBalanceWrapper getCashierBalance(@PathVariable int id) {
        return cashierBalanceController.getCashierBalanceById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public CashierBalanceWrapper createCashierBalance(@RequestBody CashierBalanceWrapper cashierBalanceWrapper)
            throws AlreadyExistCashierBalanceDayException, ParseException {
        if (cashierBalanceController.existCashierBalanceByDate(cashierBalanceWrapper.getDate())) {
            throw new AlreadyExistCashierBalanceDayException();
        }

        return cashierBalanceController.createCashierBalance(cashierBalanceWrapper);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public CashierBalanceWrapper updateCashierBalance(@PathVariable int id, @RequestBody CashierBalanceWrapper cashierBalanceWrapper)
            throws NotFoundCashierBalanceIdException {
        System.out.println("--------------------");
        System.out.println(cashierBalanceWrapper);
        System.out.println("--------------------");
        if (!cashierBalanceController.existCashierBalanceId(id)) {
            throw new NotFoundCashierBalanceIdException();
        }

        return cashierBalanceController.updateCashierBalance(cashierBalanceWrapper);
    }
}
