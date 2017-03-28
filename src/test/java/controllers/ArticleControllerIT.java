package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.core.AlarmType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wrappers.ArticleFilterWrapper;
import wrappers.ArticleWrapper;
import wrappers.ProductsOutFilterWrapper;
import wrappers.ProviderWrapper;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ArticleControllerIT {

    @Autowired
    private ArticleController articleController;

    @Autowired
    private ProviderController providerController;

    @Test
    public void testUpdateStock() {
        int stock = articleController.getAll().get(0).getStock();
        articleController.updateStock(articleController.getAll().get(0), stock + 5);
        assertEquals(stock + 5, articleController.getAll().get(0).getStock());
        articleController.updateStock(articleController.getAll().get(0), stock);
    }

    @Test
    public void testSearchWithTwoParameters() {
        ProviderWrapper provider = providerController.getAll().get(0);
        List<ArticleWrapper> list = articleController.search(provider.getId(), AlarmType.WARNING);
        assertEquals(false, list.isEmpty());
        assertEquals(true, list.get(0).getProviderID() == provider.getId());
    }

    @Test
    public void testSearchByProvier() {
        ProviderWrapper provider = providerController.getAll().get(0);
        List<ArticleWrapper> list = articleController.search(provider.getId(), null);
        assertEquals(false, list.isEmpty());
        assertEquals(true, list.get(0).getProviderID() == provider.getId());
    }

    @Test
    public void testSearchByTypeAlarm() {
        List<ArticleWrapper> list = articleController.search(0, AlarmType.WARNING);
        assertEquals(false, list.isEmpty());
    }
 
    @Test
    public void testGetArticlesByFilter() {
        ArticleFilterWrapper articleFront= new ArticleFilterWrapper ();
        articleFront.setDescription("");
        articleFront.setReference("");
        articleFront.setMaxRetailPrice(new BigDecimal("0"));
        articleFront.setMinRetailPrice(new BigDecimal("0"));
        articleFront.setMaxWholesalePrice(new BigDecimal("0"));
        articleFront.setMinWholesalePrice(new BigDecimal("1"));
        articleFront.setStock(0);
        List<ProductsOutFilterWrapper> productosOut=articleController.getArticlesByFilter(articleFront);
        assertNotNull(productosOut);
        assertTrue(productosOut.size()>1);
    }
    
    @Test
    public void testGetArticlesByFilterExtrem() {
        ArticleFilterWrapper articleFront= new ArticleFilterWrapper ();
        articleFront.setDescription("");
        articleFront.setReference("");
        articleFront.setMaxRetailPrice(new BigDecimal("0"));
        articleFront.setMinRetailPrice(new BigDecimal("0"));
        articleFront.setMaxWholesalePrice(new BigDecimal("0"));
        articleFront.setMinWholesalePrice(new BigDecimal("10000000000000"));
        articleFront.setStock(0);
        assertEquals(0,articleController.getArticlesByFilter(articleFront).size());
        articleFront.setMaxWholesalePrice(new BigDecimal("1"));
        articleFront.setMinWholesalePrice(new BigDecimal("10"));
        assertEquals(0,articleController.getArticlesByFilter(articleFront).size());
    
    }
}
