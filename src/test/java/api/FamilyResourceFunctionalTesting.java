package api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import wrappers.FamilyWrapper;


public class FamilyResourceFunctionalTesting {
    
    @Test
    public void testSearchByName() {
        FamilyWrapper familyWrapper = new RestBuilder<FamilyWrapper>(RestService.URL).path(Uris.FAMILIES).pathId("family")
                .clazz(FamilyWrapper.class).post().build();
        assertEquals("family", familyWrapper.getName());
    }
}
