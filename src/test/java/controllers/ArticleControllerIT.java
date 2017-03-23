package controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.core.AlarmType;
import wrappers.ArticleWrapper;
import wrappers.ProviderWrapper;

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
        assertEquals(true, list.get(0).getProviderWrapper().getId() == provider.getId());
    }
    
    @Test
    public void testSearchByProvier() {
        ProviderWrapper provider = providerController.getAll().get(0);
        List<ArticleWrapper> list = articleController.search(provider.getId(), null);
        assertEquals(false, list.isEmpty());
        assertEquals(true, list.get(0).getProviderWrapper().getId() == provider.getId());
    }
    
    @Test
    public void testSearchByTypeAlarm() {
        List<ArticleWrapper> list = articleController.search(0, AlarmType.WARNING);
        assertEquals(false, list.isEmpty());
    }

}
