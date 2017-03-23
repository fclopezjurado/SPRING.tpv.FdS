package api;

import org.junit.Test;
import wrappers.EmbroideryFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmbroideryResourceFunctionalTesting {

    @Test
    public void testGetEmbroideryByFilterMock() {
        EmbroideryFilterWrapper embroideryFilterWrapper = new EmbroideryFilterWrapper();
        embroideryFilterWrapper.setDescription("descripcion");
        embroideryFilterWrapper.setReference("reference");
        embroideryFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        embroideryFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        embroideryFilterWrapper.setMinStitches(0);
        embroideryFilterWrapper.setMaxStitches(0);
        embroideryFilterWrapper.setMinColors(0);
        embroideryFilterWrapper.setMaxColors(0);
        embroideryFilterWrapper.setMinSquareMillimeters(0);
        embroideryFilterWrapper.setMaxSquareMillimeters(0);

        List<ProductsOutFilterWrapper> productosSalidaMock = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>(RestService.URL)
                .path(Uris.EMBROIDERY + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(embroideryFilterWrapper).post().build());
        assertEquals(1, productosSalidaMock.size());
    }
}
