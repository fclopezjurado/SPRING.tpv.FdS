package daos.core;

import entities.core.Alarm;
import entities.core.AlarmType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AlarmDao extends JpaRepository<Alarm, Integer> {

    Alarm findById(int id);
    
    @Transactional
    Long deleteById(int id);

    @Query("SELECT a FROM Alarm a join a.articleList art where art.id = ?1")
    List<Alarm> findByArticleListContaining(long id);

    List<Alarm> findByType(AlarmType type);
    
}
