package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = Uris.FILTER+"SinMock", method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilter(@RequestBody ArticleFilterWrapper article) {
        List<ProductsOutFilterWrapper> productosSalida = this.articleController.getArticlesByFilter(article);
        return productosSalida;
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeArticle(@PathVariable(value = "id") long id) {
        System.out.println(id);
        this.articleController.removeArticle(id);
    }

    @RequestMapping(value = Uris.ARTICLES + Uris.ID, method = RequestMethod.PUT)
    public void updateArticle(@RequestBody ArticleWrapper articleWrapper) {
        this.articleController.updateArticle(articleWrapper);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void addArticle(@RequestBody ArticleWrapper article) {
        this.articleController.add(article);
    }

}
