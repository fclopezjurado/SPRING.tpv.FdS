package api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.core.AlarmType;
import wrappers.ArticleFilterWrapper;
import wrappers.ArticleWrapper;
import wrappers.ProductsOutFilterWrapper;
import wrappers.ProviderWrapper;

public class ArticleResourceFunctionalTesting {

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void testSearchByTwoParameters() {
        ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type", AlarmType.WARNING.toString())
                .clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderWrapper().getId() == providerWrapper.getId());
    }

    @Test
    public void testSearchByProvider() {
        ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", String.valueOf(providerWrapper.getId())).param("type", null)
                .clazz(ArticleWrapper[].class).get().build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderWrapper().getId() == providerWrapper.getId());
    }

    @Test
    public void testSearchByType() {
        ProviderWrapper providerWrapper = Arrays.asList(new RestBuilder<ProviderWrapper[]>(RestService.URL).path(Uris.PROVIDERS)
                .clazz(ProviderWrapper[].class).get().build()).get(0);
        List<ArticleWrapper> articles = Arrays.asList(new RestBuilder<ArticleWrapper[]>(RestService.URL).path(Uris.ARTICLES)
                .path(Uris.SEARCH).param("provider", "0").param("type", AlarmType.WARNING.toString()).clazz(ArticleWrapper[].class).get()
                .build());
        assertEquals(false, articles.isEmpty());
        assertEquals(true, articles.get(0).getProviderWrapper().getId() == providerWrapper.getId());
    }

    @Test
    public void testGetArticleByFilterMock() {
        ArticleFilterWrapper articleFilterWrapper = new ArticleFilterWrapper();
        articleFilterWrapper.setDescription("descripcion");
        articleFilterWrapper.setReference("reference");
        articleFilterWrapper.setMinRetailPrice(new BigDecimal("0"));
        articleFilterWrapper.setMaxRetailPrice(new BigDecimal("0"));
        articleFilterWrapper.setStock(1);
        articleFilterWrapper.setMinWholesalePrice(new BigDecimal("0"));
        articleFilterWrapper.setMaxWholesalePrice(new BigDecimal("0"));
        List<ProductsOutFilterWrapper> productosSalidaMock = Arrays.asList(new RestBuilder<ProductsOutFilterWrapper[]>
                (RestService.URL).path(Uris.ARTICLES + Uris.FILTER).clazz(ProductsOutFilterWrapper[].class).body(articleFilterWrapper)
                .post().build());
        assertEquals(1, productosSalidaMock.size());
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
