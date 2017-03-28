package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;


import entities.core.AlarmType;
import wrappers.ArticleFilterWrapper;
import wrappers.ArticleWrapper;
import wrappers.ProductsOutFilterWrapper;
import wrappers.ProviderWrapper;

public class ArticleResourceFunctionalTesting {
    
    private String token;
    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
        token = new RestService().loginAdmin();
    }

    @Test
    public void testSearchByTwoParameters() {
        ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type", AlarmType.WARNING.toString())
                .clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderID() == providerWrapper.getId());
    }

    @Test
    public void testSearchByProvider() {
        ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type", null)
                .clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderID() == providerWrapper.getId());
    }

    @Test
    public void testSearchByType() {
        ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", "0").param("type", AlarmType.WARNING.toString()).clazz(ArticleWrapper[].class).get()
                .build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderID() == providerWrapper.getId());
    }

   

    
    @Test
    public void testGetArticleByFilter() {
        ArticleFilterWrapper articleFilterWrapper = new ArticleFilterWrapper();
        articleFilterWrapper.setDescription("");
        articleFilterWrapper.setReference("");
        articleFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        articleFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        articleFilterWrapper.setStock(0);
        articleFilterWrapper.setMinWholesalePrice(new BigDecimal("0"));
        articleFilterWrapper.setMaxWholesalePrice(new BigDecimal("0"));
        List<ProductsOutFilterWrapper> productosSalida = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>
                (RestService.URL).path(Uris.ARTICLES + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(articleFilterWrapper).basicAuth(token, "")
                .post().build());
        assertTrue(productosSalida.size()>0);
    }
    
    
    @Test
    public void testGetArticleByFilterExtrem() {
        ArticleFilterWrapper articleFilterWrapper = new ArticleFilterWrapper();
        articleFilterWrapper.setDescription("");
        articleFilterWrapper.setReference("");
        articleFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        articleFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        articleFilterWrapper.setStock(0);
        articleFilterWrapper.setMinWholesalePrice(new BigDecimal("10000000000000"));
        articleFilterWrapper.setMaxWholesalePrice(new BigDecimal("0"));
        List<ProductsOutFilterWrapper> productosSalida = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>
                (RestService.URL).path(Uris.ARTICLES + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(articleFilterWrapper).basicAuth(token, "")
                .post().build());
        assertEquals(0,productosSalida.size());
        
        articleFilterWrapper.setMinWholesalePrice(new BigDecimal("10"));
        articleFilterWrapper.setMaxWholesalePrice(new BigDecimal("1"));
        List<ProductsOutFilterWrapper> productosSalida2 = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>
                (RestService.URL).path(Uris.ARTICLES + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(articleFilterWrapper).basicAuth(token, "")
                .post().build());
        assertEquals(0,productosSalida2.size());
    }
    
    @Test(expected=HttpClientErrorException.class)
    public void testGetArticleByFilterException() {
        ArticleFilterWrapper articleFilterWrapper = new ArticleFilterWrapper();
        articleFilterWrapper.setDescription(null);
        new RestBuilder<ProductsOutFilterWrapper[]> (RestService.URL).path(Uris.ARTICLES + Uris.FILTER).body(articleFilterWrapper).basicAuth(token, "") 
        .post().build();

    }
    
    @After
    public void after() {
        new RestService().deleteAll();
    }

}
