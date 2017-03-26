package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.CashierBalanceDao;
import entities.core.CashierBalance;
import wrappers.CashierBalanceWrapper;
import wrappers.CashierBalancesListWrapper;

@Controller
public class CashierBalanceController {

    private CashierBalanceDao cashierBalanceDao;

    @Autowired
    public void setCashierBalanceDao(CashierBalanceDao cashierBalanceDao) {
        this.cashierBalanceDao = cashierBalanceDao;
    }

    public List<CashierBalanceWrapper> getAll() {
        CashierBalancesListWrapper cashierBalancesWrapper = new CashierBalancesListWrapper();
        cashierBalancesWrapper.wrapCashierBalances(cashierBalanceDao.findAll());
        return cashierBalancesWrapper;
    }

    public CashierBalanceWrapper createCashierBalance(CashierBalanceWrapper cashierBalanceWrapper) {
        CashierBalance balance = new CashierBalance(cashierBalanceWrapper.getChange(), cashierBalanceWrapper.getTotal(),
                cashierBalanceWrapper.getCash(), cashierBalanceWrapper.getChecks(), cashierBalanceWrapper.getDataphone());
        cashierBalanceDao.save(balance);
        cashierBalanceDao.flush();
        cashierBalanceWrapper.setId(cashierBalanceDao.findOneByDay(balance.getDay()).getId());
        return cashierBalanceWrapper;
    }

    public CashierBalanceWrapper getCashierBalanceById(int id) {
        CashierBalance balance = cashierBalanceDao.findOne(id);
        return new CashierBalanceWrapper(balance.getId(), balance.getChange(), balance.getTotalTiketsMoney(), balance.getChecks(),
                balance.getDataphone(), balance.getDay());
    }

    public boolean existCashierBalanceByDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CashierBalanceWrapper.dateFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(dateFormat.parse(date));
        } catch (ParseException e) {
            return false;
        }
        return cashierBalanceDao.findOneByDay(cal) != null;
    }

    public boolean existCashierBalanceId(int id) {
        return cashierBalanceDao.findOne(id) != null;
    }

    public CashierBalanceWrapper updateCashierBalance(CashierBalanceWrapper cashierBalanceWrapper) {
        CashierBalance balance = new CashierBalance(cashierBalanceWrapper.getChange(), cashierBalanceWrapper.getTotal(),
                cashierBalanceWrapper.getCash(), cashierBalanceWrapper.getChecks(), cashierBalanceWrapper.getDataphone());
        balance.setId(cashierBalanceWrapper.getId());
        cashierBalanceDao.save(balance);
        cashierBalanceDao.flush();
        return cashierBalanceWrapper;
    }

}
