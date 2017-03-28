package daos.core;

import java.util.List;

import entities.core.Article;
import wrappers.ArticleFilterWrapper;

public interface  ArticleExtended {

    public List<Article> findArticlesByFilter(ArticleFilterWrapper article);
}
