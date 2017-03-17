package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import entities.core.Article;
import wrappers.ArticleWrapper;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articlesDao;

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articlesDao = articleDao;
    }

    public List<ArticleWrapper> getAll() {
        List<Article> articles = articlesDao.findAll();
        List<ArticleWrapper> articleWrapperList = new ArrayList<ArticleWrapper>();
        for(Article article : articles) {
            articleWrapperList.add(new ArticleWrapper(article.getId(), article.getReference(), article.getDescription(), article.getRetailPrice(), article.getStock(), article.getWholesalePrice(), null));
        }
        return articleWrapperList;
    }

    public void updateStock(ArticleWrapper article, int newStock) {
        Article a = articlesDao.findById(article.getId());
        a.setStock(newStock);
        articlesDao.save(a);
    }
}
