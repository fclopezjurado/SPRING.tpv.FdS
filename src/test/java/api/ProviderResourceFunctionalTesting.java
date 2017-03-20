package api;

import org.junit.Test;

import wrappers.ProviderWrapper;
import wrappers.ProvidersWrapper;

public class ProviderResourceFunctionalTesting {

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
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).param("id", "1").delete().build();
    }

}
