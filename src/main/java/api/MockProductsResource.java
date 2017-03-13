package api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wrappers.MockProductsWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.MOCK_PRODUCT)
public class MockProductsResource {

    @RequestMapping(method = RequestMethod.GET)
    public MockProductsWrapper getAll(){
        MockProductsWrapper result = new MockProductsWrapper();
        ArrayList<String> list = new ArrayList<String>();
        list.add("producto1");
        list.add("producto2");
        list.add("producto3");
        list.add("producto4");
        result.setProducts(list);
        return result;
    }
    
}
