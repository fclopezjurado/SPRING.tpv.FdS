package api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


import wrappers.ProductsOutFilterWrapper;
import wrappers.TextilePritingFilterWrapper;

public class TextilePrintingResourceFunctionalTesting {
    @Test
    public void testGetTextilePrintingByFilterMock() {
        TextilePritingFilterWrapper textilePrintingFilterWrapper = new TextilePritingFilterWrapper();
        textilePrintingFilterWrapper.setDescription("descripcion");
        textilePrintingFilterWrapper.setReference("reference");
        textilePrintingFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        textilePrintingFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        textilePrintingFilterWrapper.setType("type");
        List<ProductsOutFilterWrapper> productosSalidaMock = Arrays
                .asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL).path(Uris.TEXTILE_PRINTING + Uris.FILTER)
                        .clazz(ProductsOutFilterWrapper[].class).body(textilePrintingFilterWrapper).post().build());
        assertEquals(1, productosSalidaMock.size());
    }
}
