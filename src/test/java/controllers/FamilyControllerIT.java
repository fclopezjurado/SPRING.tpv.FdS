package controllers;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.ArticleWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class FamilyControllerIT {
    
    @Autowired
    private FamilyController familyController;
    
    @Autowired
    private ArticleController articleController;
    
    @Test
    public void testGetAllFamilies(){
        assertEquals(3, familyController.getAllFamilies().size());
    }
    
    @Test
    public void testGetProductsByFamilyName(){
        List<ProductsFilterByFamilyWrapper> productsFilterByFamilyWrappers1 = familyController
                .getProductsByFamilyName("familyName1");
        List<ProductsFilterByFamilyWrapper> productsFilterByFamilyWrappers2 = familyController
                .getProductsByFamilyName("familyName3");
        assertEquals(12, productsFilterByFamilyWrappers2.size());
        ArticleWrapper articleWrapper = articleController.getAll().get(0);
        assertEquals(articleWrapper.getId(), productsFilterByFamilyWrappers1.get(0).getId());
    }

}
