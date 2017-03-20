package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundCashierBalanceDayException;
import api.exceptions.AlreadyExistCashierBalanceDayException;
import controllers.CashierBalanceController;
import wrappers.CashierBalanceWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.CASHIER_BALANCE)
public class CashierBalanceResource {

    private CashierBalanceController cashierBalanceController;

    @Autowired
    public void setArticleController(CashierBalanceController cashierBalanceController) {
        this.cashierBalanceController = cashierBalanceController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CashierBalanceWrapper> getAllCashierBalances() {
        return cashierBalanceController.getAll();
    }

    @RequestMapping(value = Uris.REFERENCE, method = RequestMethod.GET)
    public CashierBalanceWrapper getCashierBalance(@PathVariable int id) {
        return cashierBalanceController.getCashierBalanceById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public CashierBalanceWrapper createCashierBalance(@RequestBody CashierBalanceWrapper cashierBalanceWrapper)
            throws AlreadyExistCashierBalanceDayException {
        if (cashierBalanceController.existCashierBalanceByDate(cashierBalanceWrapper.getDate())) {
            throw new AlreadyExistCashierBalanceDayException();
        }

        return cashierBalanceController.createCashierBalance(cashierBalanceWrapper);
    }

    @RequestMapping(value = Uris.ID, method = RequestMethod.PUT)
    public CashierBalanceWrapper updateCashierBalance(@RequestBody CashierBalanceWrapper cashierBalanceWrapper)
            throws NotFoundCashierBalanceDayException {
        if (!cashierBalanceController.existCashierBalanceByDate(cashierBalanceWrapper.getDate())) {
            throw new NotFoundCashierBalanceDayException();
        }

        return cashierBalanceController.createCashierBalance(cashierBalanceWrapper);
    }
}
