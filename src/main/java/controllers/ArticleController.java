package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import entities.core.Article;

@Controller
public class ArticleController {

    
    @Autowired
    private ArticleDao articlesDao;
    
    @Autowired
    public void setArticleDao(ArticleDao articlesDao) {
        this.articlesDao = articlesDao;
    }
    
    
    public List<Article> getAll() {
        List<Article> articles = articlesDao.findAll();
        return articles;

    }
}
