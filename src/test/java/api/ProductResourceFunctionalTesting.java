package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.ProductFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

public class ProductResourceFunctionalTesting {
    private String token;

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
        token = new RestService().loginAdmin();
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

    @Test
    public void testGetProductsByFilter() {
        ProductFilterWrapper productsFilterWrapper = new ProductFilterWrapper();
        productsFilterWrapper.setDescription("");
        productsFilterWrapper.setReference("");
        productsFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        productsFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        List<ProductsOutFilterWrapper> productosSalida = Arrays
                .asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.PRODUCTS + Uris.FILTER)
                        .clazz(ProductsOutFilterWrapper[].class).body(productsFilterWrapper).basicAuth(token, "").post().build());
        assertTrue(productosSalida.size()>0);
    }
    
    @Test
    public void testGetProductsByFilterExtreme() {
        ProductFilterWrapper productsFilterWrapper = new ProductFilterWrapper();
        productsFilterWrapper.setDescription("");
        productsFilterWrapper.setReference("");
        productsFilterWrapper.setMinRetailPrice(new BigDecimal("1000000000000"));
        productsFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        List<ProductsOutFilterWrapper> productosSalida = Arrays
                .asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.PRODUCTS + Uris.FILTER)
                        .clazz(ProductsOutFilterWrapper[].class).body(productsFilterWrapper).basicAuth(token, "").post().build());
        assertEquals(0,productosSalida.size());
        productsFilterWrapper.setMinRetailPrice(new BigDecimal("10"));
        productsFilterWrapper.setMaxRetailPrice(new BigDecimal("1"));
        List<ProductsOutFilterWrapper> productosSalida2 = Arrays
                .asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.PRODUCTS + Uris.FILTER)
                        .clazz(ProductsOutFilterWrapper[].class).body(productsFilterWrapper).basicAuth(token, "").post().build());
        assertEquals(0,productosSalida2.size());
    }
    
    @Test(expected=HttpClientErrorException.class)
    public void testGetProductsByFilterException() {
        ProductFilterWrapper productsFilterWrapper = new ProductFilterWrapper();
        productsFilterWrapper.setDescription(null);
        new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.PRODUCTS + Uris.FILTER)
        .clazz(ProductsOutFilterWrapper[].class).body(productsFilterWrapper).basicAuth(token, "").post().build();
    }
}
