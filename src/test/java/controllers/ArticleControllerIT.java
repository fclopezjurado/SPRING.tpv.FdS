package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class ArticleControllerIT {

    @Autowired
    private ArticleController articleController;

    @Test
    public void testUpdateStock() {
        int stock = articleController.getAll().get(0).getStock();
        articleController.updateStock(articleController.getAll().get(0), stock + 5);
        assertEquals(stock + 5, articleController.getAll().get(0).getStock());
        articleController.updateStock(articleController.getAll().get(0), stock);
    }

}
