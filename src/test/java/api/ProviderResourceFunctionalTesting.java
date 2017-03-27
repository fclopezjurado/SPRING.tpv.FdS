package api;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.ArticleWrapper;
import wrappers.ProviderWrapper;

public class ProviderResourceFunctionalTesting {
    
    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }
    
    @After
    public void after() {
        new RestService().deleteAll();
    }
    
    @Test
    public void testCreateProvider() {
        ProviderWrapper providerWrapper = new ProviderWrapper();
        providerWrapper.setCompany("Compañia");
        providerWrapper.setMobile(666666672L);
        assertNotNull(new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).body(providerWrapper).clazz(ProviderWrapper.class).post().build());
    }

    @Test
    public void testGetAll() {
    	 List<ProviderWrapper> providers = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS).get()
                .clazz(ProviderWrapper[].class).build());
        for (ProviderWrapper providerWrapper : providers) {
            System.out.println(providerWrapper.toString());
        }
        assertNotNull(providers);
    }

    @Test
    public void testUpdateProviders() {
    	ProviderWrapper providerCreated = createProviderWithoutArticles();
    	
    	ProviderWrapper providerToUpdate = new ProviderWrapper(providerCreated.getId(), "", "", 6666L, 0L, "", "");
    	providerToUpdate.setMobile(9999);
    	ProviderWrapper providerUpdated = new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).body(providerToUpdate).clazz(ProviderWrapper.class).put().build();
    	
    	assertEquals(providerUpdated.getId(), providerToUpdate.getId());
    	assertEquals(providerUpdated.getId(), providerCreated.getId());
    	
    	assertEquals(providerUpdated.getMobile(), providerToUpdate.getMobile());
    	assertNotEquals(providerUpdated.getMobile(), providerCreated.getMobile());

    }

    @Test
    public void testDeleteProviders() {
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).path("/"+ createProviderWithoutArticles().getId()).delete().build();
    }
    
    @Test(expected = HttpClientErrorException.class)
    public void testDeleteProvidersException() {
    	
        new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).path("/"+ searchProviderWithArticles()).delete().build();
    }
    
    public int searchProviderWithArticles() {
    	ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type", null)
                .clazz(ArticleWrapper[].class).get().build());
        return articles.get(0).getProviderID();
    }
    
    public ProviderWrapper createProviderWithoutArticles() {
        ProviderWrapper providerWrapper = new ProviderWrapper();
        providerWrapper.setCompany("Compañia");
        providerWrapper.setMobile(899);
        providerWrapper.setMobile(System.currentTimeMillis());
        ProviderWrapper inserted = new RestBuilder<ProviderWrapper>(RestService.URL).path(Uris.PROVIDERS).clazz(ProviderWrapper.class).body(providerWrapper).post().build();
        
        return inserted;
    }

}
