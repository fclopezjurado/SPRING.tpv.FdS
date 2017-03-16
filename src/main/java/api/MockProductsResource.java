package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.ArticleController;
import wrappers.MockProductsWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.MOCK_PRODUCT)
public class MockProductsResource {

    private ArticleController articleController;
    
    @Autowired
    public void setProductsController(ArticleController articleController){
        this.articleController = articleController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public MockProductsWrapper getAll(){
        MockProductsWrapper result = new MockProductsWrapper();
        result.setProducts(articleController.getAll());
        return result;
    }
    
}
