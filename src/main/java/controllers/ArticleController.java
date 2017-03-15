package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import daos.core.ArticleDao;
import entities.core.Article;

public class ArticleController {

    @Autowired
    private static ArticleDao articlesDao;
    
    public List<Article> getAll() {
        List<Article> articles = articlesDao.findAll();
        return articles;

    }
}
