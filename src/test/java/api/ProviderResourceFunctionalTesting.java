package api;

import org.junit.Test;

import wrappers.ProviderWrapper;

public class ProviderResourceFunctionalTesting {

    @Test
    public void testCreateProvider() {
        new RestBuilder<Object>(RestService.URL).path(Uris.PROVIDERS).body(new ProviderWrapper()).post().build();
    }

    @Test
    public void testRecoverProviders() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).param("id", "143").get().build();
    }

    @Test
    public void testUpdateProviders() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).body(new ProviderWrapper()).put().build();
    }

    @Test
    public void testDeleteProviders() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).param("id", "143").delete().build();
    }

}
