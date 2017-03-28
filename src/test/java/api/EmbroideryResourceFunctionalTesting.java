package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.EmbroideryFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmbroideryResourceFunctionalTesting {
    
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
    public void testGetEmbroideryByFilter() {
        EmbroideryFilterWrapper embroideryFilterWrapper = new EmbroideryFilterWrapper();
        embroideryFilterWrapper.setDescription("");
        embroideryFilterWrapper.setReference("");
        embroideryFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        embroideryFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        embroideryFilterWrapper.setMinStitches(0);
        embroideryFilterWrapper.setMaxStitches(10000);
        embroideryFilterWrapper.setMinColors(0);
        embroideryFilterWrapper.setMaxColors(100);
        embroideryFilterWrapper.setMinSquareMillimeters(0);
        embroideryFilterWrapper.setMaxSquareMillimeters(100);

        List<ProductsOutFilterWrapper> productosSalida = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL)
                .path(Uris.EMBROIDERY + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(embroideryFilterWrapper).basicAuth(token, "").post().build());
        assertTrue(productosSalida.size()>0);
    }
    
    
    @Test
    public void testGetEmbroideryByFilterExtrem() {
        EmbroideryFilterWrapper embroideryFilterWrapper = new EmbroideryFilterWrapper();
        embroideryFilterWrapper.setDescription("");
        embroideryFilterWrapper.setReference("");
        embroideryFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        embroideryFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        embroideryFilterWrapper.setMinStitches(1000000000);
        embroideryFilterWrapper.setMaxStitches(0);
        embroideryFilterWrapper.setMinColors(0);
        embroideryFilterWrapper.setMaxColors(100);
        embroideryFilterWrapper.setMinSquareMillimeters(10);
        embroideryFilterWrapper.setMaxSquareMillimeters(1);
        List<ProductsOutFilterWrapper> productosSalida = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL)
                .path(Uris.EMBROIDERY + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(embroideryFilterWrapper).basicAuth(token, "").post().build());
        assertEquals(0,productosSalida.size());
        
        
    }
    
    @Test(expected=HttpClientErrorException.class)
    public void testGetEmbroideryByFilterException() {
        EmbroideryFilterWrapper embroideryFilterWrapper = new EmbroideryFilterWrapper();
        embroideryFilterWrapper.setDescription(null);
        new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL)
        .path(Uris.EMBROIDERY + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(embroideryFilterWrapper).basicAuth(token, "").post().build();
    }
}
