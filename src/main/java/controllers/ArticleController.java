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
    public void setArticleDao(ArticleDao articlesDao) {
        this.articlesDao = articlesDao;
    }
    
    
    public List<ArticleWrapper> getAll() {
        List<Article> articleList =new ArrayList<>();
        List<ArticleWrapper> articleWrapperList = new ArrayList<>();
        articleList= articlesDao.findAll();
        for (Article article : articleList) {
        	articleWrapperList.add(new ArticleWrapper(article));
			
		}
        return articleWrapperList;

    }
}
