package api;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import wrappers.ProviderWrapper;

public class ProviderResourceFunctionalTesting {
    
    public static String id = "1";
    
    @Before
    public void setUp() {
        List<ProviderWrapper> providers = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).build());
        
        if(providers!=null  && providers.size() >0 && providers.get(0)!=null){
            id = String.valueOf(providers.get(0).getId());
        }

    }
    
    @Test
    public void testCreateProvider() {
        ProviderWrapper providerWrapper = new ProviderWrapper();
        providerWrapper.setCompany("Compañia");
        providerWrapper.setMobile(666666672L);
        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(providerWrapper).post().build();
    }

    @Test
    public void testGetAll() {
        List<ProviderWrapper> providers = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).build());
        for (ProviderWrapper providerWrapper : providers) {
            System.out.println(providerWrapper.toString());
        }
    }

    @Test
    public void testUpdateProviders() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).body(new ProviderWrapper()).put().build();
    }

    @Test
    public void testDeleteProviders() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).param("id", id).delete().build();
    }

}
