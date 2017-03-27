package api;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import api.exceptions.ProviderWithArticlesException;
import entities.core.Provider;
import wrappers.ProviderWrapper;

public class ProviderResourceFunctionalTesting {
    
    public static int id = 1;
    
    
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
    	Provider provider = new Provider("company", "address", 173656738675748927L, 666, "paymentConditions", "note");
    	ProviderWrapper providerWrapper = new ProviderWrapper(provider);
    	Provider p = new RestBuilder<Provider>(RestService.URL).path(Uris.PROVIDERS).body(providerWrapper).clazz(Provider.class).post().build();
    	id = p.getId();
    	
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).path("/"+ id).delete().build();
    }
    
    @Test(expected = HttpClientErrorException.class)
    public void testDeleteProvidersException() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).path("/"+ 1).delete().build();
    }

}
