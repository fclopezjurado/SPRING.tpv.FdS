package api;

import org.junit.Test;
import wrappers.ProductFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductResourceFunctionalTesting {

    @Test
    public void testGetProductsByFilterMock() {
        ProductFilterWrapper productsFilterWrapper = new ProductFilterWrapper();
        productsFilterWrapper.setDescription("descripcion");
        productsFilterWrapper.setReference("reference");
        productsFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        productsFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        List<ProductsOutFilterWrapper> productosSalidaMock = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>
                (RestService.URL).path(Uris.PRODUCTS).clazz(ProductsOutFilterWrapper[].class).body(productsFilterWrapper).post().build());
        assertEquals(1, productosSalidaMock.size());
    }
}
