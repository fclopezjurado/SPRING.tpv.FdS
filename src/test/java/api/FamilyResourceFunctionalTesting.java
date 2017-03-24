package api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import wrappers.FamilyWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

public class FamilyResourceFunctionalTesting {

    @Before
    public void seedDataBase() {
        new RestService().seedDatabase();
    }

    @Test
    public void testSearchByName() {
        List<ProductsFilterByFamilyWrapper> productsFilterByFamilyWrappers = Arrays
                .asList(new RestBuilder<ProductsFilterByFamilyWrapper[]>(RestService.URL).path(Uris.FAMILIES).pathId("familyName3")
                        .clazz(ProductsFilterByFamilyWrapper[].class).post().build());
        assertEquals(12, productsFilterByFamilyWrappers.size());
    }

    @Test
    public void testGetAllFamilies() {
        List<FamilyWrapper> familyWrappers = Arrays
                .asList(new RestBuilder<FamilyWrapper[]>(RestService.URL).path(Uris.FAMILIES).clazz(FamilyWrapper[].class).get().build());
        assertEquals(3, familyWrappers.size());
    }

}
