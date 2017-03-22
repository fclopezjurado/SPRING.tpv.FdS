package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import daos.core.ProviderDao;
import entities.core.AlarmType;
import entities.core.Article;
import entities.core.TextilePrinting;
import wrappers.ArticleWrapper;
import wrappers.TextilePrintingWrapper;

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
        List<Article> articleList = articlesDao.findAll();
        return articleListToArticleWrapperList(articleList);
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
    
    private List<ArticleWrapper> articleListToArticleWrapperList(List<Article> articleList) {
        List<ArticleWrapper> articleWrapperList = new ArrayList<ArticleWrapper>();
        for(Article article : articleList) {
            articleWrapperList.add(new ArticleWrapper(article));
        }
        return articleWrapperList;
    }

    public void removeArticle(long id) {
        Article article = articlesDao.findOne(id);
        articlesDao.delete(article);
    }
    
    public void updateWholeSalePrice(ArticleWrapper article, BigDecimal newWholeSalePrice) {
        Article a = articlesDao.findById(article.getId());
        a.setWholesalePrice(newWholeSalePrice);
        articlesDao.save(a);
    }
    
    public void updateArticle(ArticleWrapper articleWrapper) {
        Article  article = articlesDao.findOne(articleWrapper.getId());
        if (article != null) {
            article.setDescription(articleWrapper.getDescription());
            article.setReference(articleWrapper.getReference());
            article.setRetailPrice(articleWrapper.getRetailPrice());
            article.setStock(articleWrapper.getStock());
            article.setWholesalePrice(articleWrapper.getWholesalePrice());
            
            this.articlesDao.save(article);
        }
    }
}
