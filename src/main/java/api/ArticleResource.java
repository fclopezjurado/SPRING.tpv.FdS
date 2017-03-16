package api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.ArticleController;
import entities.core.Article;

@RestController
@RequestMapping(Uris.VERSION + Uris.ARTICLES)


public class ArticleResource {
	 
	private ArticleController articleController;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getAll() {
        return articleController.getAll();
    }
    
}
