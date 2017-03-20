package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controllers.ArticleController;
import entities.core.AlarmType;
import wrappers.ArticleFilterWrapper;
import wrappers.ArticleWrapper;
import wrappers.ProductsOutFilterWrapper;

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
    
    
    @RequestMapping(value = "/byFilter",method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilterMock(@RequestBody ArticleFilterWrapper article){
        List<ProductsOutFilterWrapper> productosSalidaMock= new ArrayList<ProductsOutFilterWrapper> ();
        ProductsOutFilterWrapper productoMock= new ProductsOutFilterWrapper();
        productoMock.setId(0);
        productoMock.setReference("referenceMock");
        productoMock.setDescription("descriptionMock");
        productosSalidaMock.add(productoMock);
        return productosSalidaMock;
    }

}
