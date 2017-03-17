package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controllers.ArticleController;
import entities.core.AlarmType;
import entities.core.Article;

@RestController
@RequestMapping(Uris.VERSION + Uris.ARTICLES)

public class ArticleResource {
	 
    @Autowired
	private ArticleController articleController;

    @RequestMapping(method = RequestMethod.GET)
    public List<Article> getAll() {
        return articleController.getAll();
    }
    
    @RequestMapping(value = Uris.SEARCH)
    public List<Article> searchArticle(@RequestParam("provider") long provider, @RequestParam("type") AlarmType type) {
        return articleController.filter(provider, type);
    }
    
}
