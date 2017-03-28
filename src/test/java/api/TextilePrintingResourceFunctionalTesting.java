package api;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.ProductsOutFilterWrapper;
import wrappers.TextilePritingFilterWrapper;

public class TextilePrintingResourceFunctionalTesting {
    
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
    public void testGetTextilePrintingByFilter() {
        TextilePritingFilterWrapper textilePrintingFilterWrapper = new TextilePritingFilterWrapper();
        textilePrintingFilterWrapper.setDescription("");
        textilePrintingFilterWrapper.setReference("");
        textilePrintingFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        textilePrintingFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        textilePrintingFilterWrapper.setType("");
        List<ProductsOutFilterWrapper> productosSalida = Arrays
                .asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.TEXTILE_PRINTING + Uris.FILTER)
                        .clazz(ProductsOutFilterWrapper[].class).body(textilePrintingFilterWrapper).basicAuth(token, "").post().build());
        assertTrue(productosSalida.size()>0);
    }
    
    @Test(expected=HttpClientErrorException.class)
    public void testGetTextilePrintingByFilterException() {
        TextilePritingFilterWrapper textilePrintingFilterWrapper = new TextilePritingFilterWrapper();
        textilePrintingFilterWrapper.setDescription(null);
        new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.TEXTILE_PRINTING + Uris.FILTER)
        .clazz(ProductsOutFilterWrapper[].class).body(textilePrintingFilterWrapper).basicAuth(token, "").post().build();
        
    }
}
