package api;

import controllers.ArticleController;
import entities.core.AlarmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrappers.ArticleFilterWrapper;
import wrappers.ArticleWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(value = Uris.FILTER, method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilterMock(@RequestBody ArticleFilterWrapper article) {
        List<ProductsOutFilterWrapper> productosSalidaMock = new ArrayList<ProductsOutFilterWrapper>();
        ProductsOutFilterWrapper productoMock = new ProductsOutFilterWrapper();
        productoMock.setId(0);
        productoMock.setReference("referenceMock");
        productoMock.setDescription("descriptionMock");
        productosSalidaMock.add(productoMock);
        return productosSalidaMock;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeArticle(@PathVariable(value = "id") long id) {
        System.out.println(id);
        this.articleController.removeArticle(id);
    }




}
