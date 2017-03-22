package daos.core;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.CashierBalance;

public interface CashierBalanceDao extends JpaRepository<CashierBalance, Integer> {

    CashierBalance findOneByDay(Calendar day);

}
