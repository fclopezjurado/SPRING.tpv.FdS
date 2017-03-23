package daos.core;

import entities.core.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetDao extends JpaRepository<Budget, Long> {

    Budget findByReference(String reference);

    @Query("select t from Budget t where t.user.mobile = ?1")
    List<Budget> findByUserMobile(long userMobile);

}
