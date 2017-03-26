package wrappers;

import java.util.ArrayList;
import java.util.List;

import entities.core.CashierBalance;

public class CashierBalancesListWrapper extends ArrayList<CashierBalanceWrapper> {

    private static final long serialVersionUID = 1L;

    public void wrapCashierBalances(List<CashierBalance> cashierBalances) {       
       for(CashierBalance balance : cashierBalances)
           this.add(new CashierBalanceWrapper(balance.getId(), balance.getChange(), balance.getTotalTiketsMoney(), balance.getChecks(), balance.getDataphone(), balance.getDay()));
    }

}
