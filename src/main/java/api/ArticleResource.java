package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controllers.ArticleController;
import entities.core.AlarmType;
import wrappers.ArticleWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.ARTICLES)

public class ArticleResource {

	private ArticleController articleController;

	@Autowired
	public void setArticleController(ArticleController articleController) {
	    this.articleController = articleController;
	}
	
    @RequestMapping(method = RequestMethod.GET)
    public List<ArticleWrapper> getAll() {
        return articleController.getAll();
    }
    
    @RequestMapping(value = Uris.SEARCH)
    public List<ArticleWrapper> searchArticle(@RequestParam("provider") int provider, @RequestParam("type") AlarmType type) {
        return articleController.search(provider, type);
    }
    
    
    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeArticle(@PathVariable(value = "id")  long id) {
      System.out.println(id);
        this.articleController.removeArticle(id);
    }



}
