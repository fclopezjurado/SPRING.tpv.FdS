package api;

import controllers.CashierBalanceController;
import wrappers.CashierBalanceWrapper;

import java.text.SimpleDateFormat;
import java.util.*;

public class CashierBalanceControllerMock extends CashierBalanceController {

    private Map<Integer, CashierBalanceWrapper> balances;

    public CashierBalanceControllerMock() {
        balances = new HashMap<>();
        balances.put(1,
                new CashierBalanceWrapper(1, 50, 50, 50, 50, new SimpleDateFormat(CashierBalanceWrapper.dateFormat).format(new Date())));
        balances.put(2, new CashierBalanceWrapper(2, 50, 50, 50, 50, "01-01-1970"));
        balances.put(3, new CashierBalanceWrapper(3, 50, 50, 50, 50, "02-01-1970"));

    }

    @Override
    public List<CashierBalanceWrapper> getAll() {
        return new ArrayList<CashierBalanceWrapper>(balances.values());
    }

    @Override
    public CashierBalanceWrapper getCashierBalanceById(int id) {
        return balances.get(id);
    }

    @Override
    public boolean existCashierBalanceByDate(String date) {
        for (CashierBalanceWrapper balance : balances.values()) {
            if (balance.getDate().equals(date)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public CashierBalanceWrapper createCashierBalance(CashierBalanceWrapper cashierBalanceWrapper) {
        cashierBalanceWrapper.setId(balances.size() + 1);
        balances.put(cashierBalanceWrapper.getId(), cashierBalanceWrapper);
        return cashierBalanceWrapper;
    }

    @Override
    public CashierBalanceWrapper updateCashierBalance(CashierBalanceWrapper cashierBalanceWrapper) {
        balances.remove(cashierBalanceWrapper.getId());
        balances.put(cashierBalanceWrapper.getId(), cashierBalanceWrapper);
        return cashierBalanceWrapper;
    }

    @Override
    public boolean existCashierBalanceId(int id) {
        return balances.containsKey(id);
    }

}
