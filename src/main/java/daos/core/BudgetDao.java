package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Budget;

public interface BudgetDao extends JpaRepository<Budget, Long> {

    Budget findByReference(String reference);

    @Query("select t from Budget t where t.user.mobile = ?1")
    List<Budget> findByUserMobile(long userMobile);

}
