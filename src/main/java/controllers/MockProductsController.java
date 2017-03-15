package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.ArticleDao;
import entities.core.Article;

@Controller
public class MockProductsController {

    @Autowired
    private ArticleDao articleDao;
    
    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
    
    public List<Article> getAll(){
        return articleDao.findAll();
    }
    
}
