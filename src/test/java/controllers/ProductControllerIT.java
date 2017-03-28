package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.ProductFilterWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
public class ProductControllerIT {
    @Autowired
    private ProductController productController;
    @Test
    public void testGetProductsByFilter() {
        ProductFilterWrapper productoFront= new ProductFilterWrapper ();
        productoFront.setDescription("");
        productoFront.setReference("");
        productoFront.setMaxRetailPrice(new BigDecimal("0"));
        productoFront.setMinRetailPrice(new BigDecimal("1"));  
        List<ProductsOutFilterWrapper> productosOut=productController.getProductsByFilter(productoFront);
        assertNotNull(productosOut);
        assertTrue(productosOut.size()>1);
    }
    
    @Test
    public void testGetProductsByFilterExtrem() {
        ProductFilterWrapper productoFront= new ProductFilterWrapper ();
        productoFront.setDescription("");
        productoFront.setReference("");
        productoFront.setMaxRetailPrice(new BigDecimal("0"));
        productoFront.setMinRetailPrice(new BigDecimal("1000000000000"));        
        assertEquals(0,productController.getProductsByFilter(productoFront).size());
        productoFront.setMaxRetailPrice(new BigDecimal("1"));
        productoFront.setMinRetailPrice(new BigDecimal("10")); 
        assertEquals(0,productController.getProductsByFilter(productoFront).size());
    }
}
