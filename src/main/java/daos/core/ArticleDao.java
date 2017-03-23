package daos.core;

import entities.core.AlarmType;
import entities.core.Article;
import entities.core.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, Long> {

    Article findByDescription(String description);

    Article findById(long id);

    @Query("SELECT distinct list FROM Alarm al join al.articleList list where al.type = ?1 and list.stock <= al.value")
    List<Article> findArticlesWithAlarmActive(AlarmType type);

    @Query("SELECT distinct list FROM Alarm al join al.articleList list where list.provider = ?1 and list.stock <= al.value")
    List<Article> findArticlesOfOneProviderWithAlarmActive(Provider provider);

    @Query("SELECT distinct list FROM Alarm al join al.articleList list where list.provider = ?1 and al.type = ?2 and list.stock <= al.value")
    List<Article> findByProviderAndAlarmType(Provider provider, AlarmType type);
    
}
