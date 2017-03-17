package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import entities.core.AlarmType;
import entities.core.Article;

@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articlesDao;
    
    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articlesDao = articleDao;
    }
    
    public List<Article> getAll() {
        List<Article> articles = articlesDao.findAll();
        return articles;
    }
    
    public void updateStock(Article article, int newStock){
        Article a = articlesDao.findById(article.getId());
        a.setStock(newStock);
        articlesDao.save(a);
    }

    public List<Article> filter(long provider, AlarmType type) {
        List<Article> list = null;
        if(provider != 0L && type == null) {
            
        }
        return list;
    }
}
