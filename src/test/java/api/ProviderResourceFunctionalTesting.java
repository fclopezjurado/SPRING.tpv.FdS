package api;

import org.junit.Before;
import org.junit.Test;

import wrappers.ProviderWrapper;
import wrappers.ProvidersWrapper;

public class ProviderResourceFunctionalTesting {
    
    public static String id = "1";
    
    @Before
    public void setUp() {
        ProvidersWrapper providers = new RestBuilder<ProvidersWrapper>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProvidersWrapper.class).build();
        
        if(providers!=null  && providers.getProvidersWrapper()!=null
                && providers.getProvidersWrapper().size() >0 && providers.getProvidersWrapper().get(0)!=null){
            id = String.valueOf(providers.getProvidersWrapper().get(0).getId());
        }

    }
    
    @Test
    public void testCreateProvider() {
        ProviderWrapper providerWrapper = new ProviderWrapper();
        providerWrapper.setCompany("Compañia");
        providerWrapper.setMobile(666666679L);
        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(providerWrapper).post().build();
    }

    @Test
    public void testGetAll() {
        ProvidersWrapper providers = new RestBuilder<ProvidersWrapper>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProvidersWrapper.class).build();
        for (ProviderWrapper providerWrapper : providers.getProvidersWrapper()) {
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
