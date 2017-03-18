package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import daos.core.ProviderDao;
import entities.core.AlarmType;
import entities.core.Article;
import wrappers.ArticleWrapper;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articlesDao;
    
    @Autowired
    private ProviderDao providerDao;

	@Autowired
	public void setArticleDao(ArticleDao articlesDao) {
		this.articlesDao = articlesDao;
	}

    public List<ArticleWrapper> getAll() {
        List<Article> articles = articlesDao.findAll();
        return articleListToArticleWrapperList(articles);
    }

    public void updateStock(ArticleWrapper article, int newStock) {
        Article a = articlesDao.findById(article.getId());
        a.setStock(newStock);
        articlesDao.save(a);
    }

    public List<ArticleWrapper> search(int provider, AlarmType type) {
        List<Article> listArticle = null;
        if(provider != 0 && type == null) {
            listArticle = articlesDao.findArticlesOfOneProviderWithAlarmActive(providerDao.findById(provider));
        }
        else if (provider == 0 && type != null) {
            listArticle = articlesDao.findArticlesWithAlarmActive(type);
        }
        else {
            listArticle = articlesDao.findByProviderAndAlarmType(providerDao.findById(provider), type);
        }
        
        return articleListToArticleWrapperList(listArticle);
    }
    
    private List<ArticleWrapper> articleListToArticleWrapperList(List<Article> articles) {
        List<ArticleWrapper> articleWrapperList = new ArrayList<ArticleWrapper>();
        for(Article article : articles) {
            articleWrapperList.add(new ArticleWrapper(article));
        }
        return articleWrapperList;
    }

}
