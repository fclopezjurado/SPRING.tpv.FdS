package api;

import org.junit.Test;
import wrappers.FamilyWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FamilyResourceFunctionalTesting {

    @Test
    public void testSearchByName() {
        List<ProductsFilterByFamilyWrapper> productsFilterByFamilyWrappers = Arrays
                .asList(new RestBuilder<ProductsFilterByFamilyWrapper[]>(RestService.URL).path(Uris.FAMILIES).pathId("family")
                        .clazz(ProductsFilterByFamilyWrapper[].class).post().build());
        assertEquals(5, productsFilterByFamilyWrappers.size());
    }

    @Test
    public void testGetAllFamilies() {
        List<FamilyWrapper> familyWrappers = Arrays.asList(new RestBuilder<FamilyWrapper[]>(RestService.URL).path(Uris.FAMILIES)
                .clazz(FamilyWrapper[].class).get().build());
        assertEquals(4, familyWrappers.size());
    }

}
