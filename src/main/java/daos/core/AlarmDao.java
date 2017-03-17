package daos.core;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Integer> {

    Alarm findById(int id);
    
    @Transactional
    Long deleteById(int id);

    @Query("SELECT a FROM Alarm a join a.articleList art where art.id = ?1")
    List<Alarm> findByArticleListContaining(long id);
    
}
