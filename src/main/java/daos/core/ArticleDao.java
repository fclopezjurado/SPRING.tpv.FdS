package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.AlarmType;
import entities.core.Article;
import entities.core.Provider;

public interface ArticleDao extends JpaRepository<Article, Long> {

    Article findByDescription(String description);

    Article findById(long id);
    
    @Query("SELECT distinct list FROM Alarm al join al.articleList list where al.type = ?1 and list.stock <= al.value")
    List<Article> findArticlesWithAlarmActive(AlarmType type);
    
    @Query(/*value = "SELECT p.* FROM Product p "
            + "inner join Alarm_Product ap on ap.articleList_id=p.id "
            + "inner join Alarm a on a.id=ap.Alarm_id "
            + "where a.provider_id = ?1 and p.stock <= a.value"
            , nativeQuery = true*/
            "SELECT distinct list FROM Alarm al join al.articleList list where list.provider = ?1 and list.stock <= al.value")
    List<Article> findArticlesOfOneProviderWithAlarmActive(Provider provider);
    
}
