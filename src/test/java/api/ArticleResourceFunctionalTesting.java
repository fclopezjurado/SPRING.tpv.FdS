package api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.AlarmType;
import wrappers.ArticleWrapper;
import wrappers.ProviderWrapper;
import wrappers.ProvidersWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class ArticleResourceFunctionalTesting {

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }
    
    @Test
    public void testSearchByTwoParameters() {
        ProviderWrapper providerWrapper = new RestBuilder<ProvidersWrapper>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProvidersWrapper.class).get().build().getProvidersWrapper().get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type",AlarmType.WARNING.toString()).clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderWrapper().getId() == providerWrapper.getId());
    }
    
    @Test
    public void testSearchByProvider() {
        ProviderWrapper providerWrapper = new RestBuilder<ProvidersWrapper>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProvidersWrapper.class).get().build().getProvidersWrapper().get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type",null).clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderWrapper().getId() == providerWrapper.getId());
    }
    
    @Test
    public void testSearchByType() {
        ProviderWrapper providerWrapper = new RestBuilder<ProvidersWrapper>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProvidersWrapper.class).get().build().getProvidersWrapper().get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", "0").param("type",AlarmType.WARNING.toString()).clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderWrapper().getId() == providerWrapper.getId());
    }
    
    @After
    public void after() {
        new RestService().deleteAll();
    }
    
}
