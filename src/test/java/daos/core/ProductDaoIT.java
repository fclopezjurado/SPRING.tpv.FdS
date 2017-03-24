package daos.core;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.AlarmType;
import entities.core.Product;
import wrappers.ProductFilterWrapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class ProductDaoIT {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ProviderDao providerDao;

    @Autowired
    private EmbroideryDao embroideryDao;

    @Autowired
    private TextilePrintingDao textilePrintingDao;
    
    @Autowired
    private ProductDao productDao;

    @Test
    public void testCreateArticle() {
        assertEquals(8, articleDao.count());
    }

    @Test
    public void testCreateEmbroidery() {
        assertEquals(4, embroideryDao.count());
    }

    @Test
    public void testCreateTextilePrinting() {
        assertEquals(4, textilePrintingDao.count());
    }

    @Test
    public void testFindById() {
        assertNotNull(embroideryDao.findById(84000002222L + 0));
    }

    @Test
    public void testFindArticlesWithAlarmActive() {
        assertEquals(false, articleDao.findArticlesWithAlarmActive().isEmpty());
    }
    
    @Test
    public void testFindArticlesWithAlarmActiveByType() {
        assertEquals(false, articleDao.findArticlesWithAlarmActiveByType(AlarmType.WARNING).isEmpty());
    }

    @Test
    public void testFindArticlesOfProvidersWithAlarmActive() {
        assertEquals(false, articleDao.findArticlesOfOneProviderWithAlarmActive(providerDao.findAll().get(0)).isEmpty());
    }
    
    @Test
    public void testFindProfuctsByFilterNormal() {
        Product producto=productDao.findAll().get(0);
        ProductFilterWrapper productoFront= new ProductFilterWrapper ();
        productoFront.setDescription(producto.getDescription());
        productoFront.setReference("");
        productoFront.setMaxRetailPrice(new BigDecimal("0"));
        productoFront.setMinRetailPrice(new BigDecimal("1"));        
        assertNotNull(productDao.findProductsByFilter(productoFront));
    }
    
    @Test
    public void testFindProfuctsByFilterNormalExtremRetailPrice() {
        Product producto=productDao.findAll().get(0);
        ProductFilterWrapper productoFront= new ProductFilterWrapper ();
        productoFront.setDescription(producto.getDescription());
        productoFront.setReference("");
        productoFront.setMaxRetailPrice(new BigDecimal("0"));
        productoFront.setMinRetailPrice(new BigDecimal("1000000000000"));        
        assertEquals(0,productDao.findProductsByFilter(productoFront).size());
        productoFront.setMaxRetailPrice(new BigDecimal("1"));
        productoFront.setMinRetailPrice(new BigDecimal("10"));        
        assertEquals(0,productDao.findProductsByFilter(productoFront).size());
    }

}
