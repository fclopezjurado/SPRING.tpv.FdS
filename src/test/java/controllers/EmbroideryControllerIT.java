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
import wrappers.EmbroideryFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class EmbroideryControllerIT {
    @Autowired
    private EmbroideryController embroideryController;

    @Test
    public void testGetEmroiderysByFilter() {
        EmbroideryFilterWrapper embroideryFront= new EmbroideryFilterWrapper ();
        embroideryFront.setDescription("");
        embroideryFront.setReference("");
        embroideryFront.setMaxRetailPrice(new BigDecimal("0"));
        embroideryFront.setMinRetailPrice(new BigDecimal("0"));
        embroideryFront.setMaxColors(100);
        embroideryFront.setMinColors(1);
        embroideryFront.setMaxSquareMillimeters(100);
        embroideryFront.setMinSquareMillimeters(1);
        embroideryFront.setMaxStitches(10000);
        embroideryFront.setMinStitches(1);
        List<ProductsOutFilterWrapper> productosOut=embroideryController.getEmroiderysByFilter(embroideryFront);
        assertNotNull(productosOut);
        assertTrue(productosOut.size()>1);
    }
    
    @Test
    public void testGetEmroiderysByFilterExtrem() {
        EmbroideryFilterWrapper embroideryFront= new EmbroideryFilterWrapper ();
        embroideryFront.setDescription("");
        embroideryFront.setReference("");
        embroideryFront.setMaxRetailPrice(new BigDecimal("0"));
        embroideryFront.setMinRetailPrice(new BigDecimal("0"));
        embroideryFront.setMaxColors(0);
        embroideryFront.setMinColors(1000000);
        assertEquals(0,embroideryController.getEmroiderysByFilter(embroideryFront).size());
        embroideryFront.setMaxColors(1);
        embroideryFront.setMinColors(10);
        assertEquals(0,embroideryController.getEmroiderysByFilter(embroideryFront).size());
        embroideryFront.setMaxSquareMillimeters(0);
        embroideryFront.setMinSquareMillimeters(1000000000);
        assertEquals(0,embroideryController.getEmroiderysByFilter(embroideryFront).size());
        embroideryFront.setMaxSquareMillimeters(1);
        embroideryFront.setMinSquareMillimeters(10);
        assertEquals(0,embroideryController.getEmroiderysByFilter(embroideryFront).size());
        embroideryFront.setMaxStitches(0);
        embroideryFront.setMinStitches(1000000000);
        assertEquals(0,embroideryController.getEmroiderysByFilter(embroideryFront).size());   
        embroideryFront.setMaxStitches(1);
        embroideryFront.setMinStitches(10);
        assertEquals(0,embroideryController.getEmroiderysByFilter(embroideryFront).size());
    }
}
