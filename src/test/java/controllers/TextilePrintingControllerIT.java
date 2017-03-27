package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.ProductsOutFilterWrapper;
import wrappers.TextilePritingFilterWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class TextilePrintingControllerIT {

    @Autowired
    private TextilePrintingController textilePrintingController;

    @Test
    public void testGetTextilePrintingByFilter() {
        TextilePritingFilterWrapper textilePrintingFront = new TextilePritingFilterWrapper();
        textilePrintingFront.setDescription("");
        textilePrintingFront.setReference("");
        textilePrintingFront.setMaxRetailPrice(new BigDecimal("0"));
        textilePrintingFront.setMinRetailPrice(new BigDecimal("1"));
        textilePrintingFront.setType("");
        List<ProductsOutFilterWrapper> productosOut = textilePrintingController.getTextilePrintingByFilter(textilePrintingFront);
        assertNotNull(productosOut);
        assertTrue(productosOut.size() > 1);
    }
    
    @Test
    public void testGetTextilePrintingByFilterExtrem() {
        TextilePritingFilterWrapper textilePrintingFront = new TextilePritingFilterWrapper();
        textilePrintingFront.setDescription("");
        textilePrintingFront.setReference("");
        textilePrintingFront.setType("");
        textilePrintingFront.setMaxRetailPrice(new BigDecimal("0"));
        textilePrintingFront.setMinRetailPrice(new BigDecimal("1000000000000"));        
        assertEquals(0,textilePrintingController.getTextilePrintingByFilter(textilePrintingFront).size());
        textilePrintingFront.setMaxRetailPrice(new BigDecimal("1"));
        textilePrintingFront.setMinRetailPrice(new BigDecimal("10")); 
        assertEquals(0,textilePrintingController.getTextilePrintingByFilter(textilePrintingFront).size());
    }

}
