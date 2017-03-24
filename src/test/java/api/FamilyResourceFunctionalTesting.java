package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import wrappers.FamilyWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        List<FamilyWrapper> familyWrappers = Arrays.asList(new RestBuilder<FamilyWrapper[]>(RestService.URL).path(Uris.FAMILIES)
                .clazz(FamilyWrapper[].class).get().build());
        assertEquals(3, familyWrappers.size());
    }
    
    @After
    public void after() {
        new RestService().deleteAll();
    }

}
