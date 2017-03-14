package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.MockProductsController;
import wrappers.MockProductsWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.MOCK_PRODUCT)
public class MockProductsResource {

    private MockProductsController productsController;
    
    @Autowired
    public void setProductsController(MockProductsController productsController){
        this.productsController = productsController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public MockProductsWrapper getAll(){
        MockProductsWrapper result = new MockProductsWrapper();
        result.setProducts(productsController.getAll());
        return result;
    }
    
}
