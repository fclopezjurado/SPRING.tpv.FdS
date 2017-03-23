package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.AlarmDao;
import daos.core.ArticleDao;
import daos.core.ProviderDao;
import entities.core.Alarm;
import entities.core.AlarmType;
import entities.core.Article;
import wrappers.ArticleWrapper;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articlesDao;
    
    @Autowired
    private AlarmDao alarmDao;
    
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
/**********************Pediente de solucionar*********************/
    public void removeArticle(long id) {
        List<Alarm> listAlarm = alarmDao.findByArticleListContaining(id);

        if (listAlarm.isEmpty()) {
            Article article = articlesDao.findOne(id);
            articlesDao.delete(article);
        } else {
            System.out.println(listAlarm.toString());
            for (int i = 0; i < listAlarm.size(); i++) {

                List<Article> articleList = new ArrayList<>(listAlarm.get(i).getArticleList());
                System.out.println(articleList.toString());
                for (int j = 0; j < articleList.size(); j++) {
                   
                    long reference = articleList.get(j).getId();
                    if (reference == id) {
                        System.out.println("entra");
                        articleList.remove(j);
                    }

                }
                System.out.println("final::" + articleList.toString());
                Alarm busqueda = alarmDao.findById(listAlarm.get(i).getId());
                busqueda.setArticleList(articleList);
                alarmDao.save(busqueda); 
            }
           
            Article article = articlesDao.findOne(id);
            articlesDao.delete(article);
        }
    }
    /*********************************************************/
       
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
