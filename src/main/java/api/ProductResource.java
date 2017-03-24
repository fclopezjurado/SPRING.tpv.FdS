package api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wrappers.ProductFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Uris.VERSION + Uris.PRODUCTS)
public class ProductResource {

    @RequestMapping(method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilterMock(@RequestBody ProductFilterWrapper product) {
        List<ProductsOutFilterWrapper> productosSalidaMock = new ArrayList<ProductsOutFilterWrapper>();
        ProductsOutFilterWrapper productoMock = new ProductsOutFilterWrapper();
        productoMock.setId(0);
        productoMock.setReference("referenceMock");
        productoMock.setDescription("descriptionMock");
        productosSalidaMock.add(productoMock);
        productosSalidaMock.add(productoMock);
        productosSalidaMock.add(productoMock);
        return productosSalidaMock;
    }
}
