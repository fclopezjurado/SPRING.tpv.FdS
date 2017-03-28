package wrappers;

import java.util.ArrayList;
import java.util.List;

import entities.core.CashierBalance;

public class CashierBalancesListWrapper extends ArrayList<CashierBalanceWrapper> {

    private static final long serialVersionUID = 1L;

    public void wrapCashierBalances(List<CashierBalance> cashierBalances) {  
        CashierBalanceWrapper balanceWrapper;
       for(CashierBalance balance : cashierBalances){
           balanceWrapper = new CashierBalanceWrapper();
           balanceWrapper.setId(balance.getId());
           balanceWrapper.setChange(balance.getChange());
           balanceWrapper.setChecks(balance.getChecks());
           balanceWrapper.setDataphone(balance.getDataphone());
           balanceWrapper.setTotal(balance.getTotalTiketsMoney());
           balanceWrapper.setDay(balance.getDay());
           balanceWrapper.setBalance(balance.getBalance());
           balanceWrapper.setCash(balance.getCash());
           this.add(balanceWrapper);
       }
    }

}
