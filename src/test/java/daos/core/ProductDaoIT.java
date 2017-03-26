package daos.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.AlarmType;
import entities.core.Product;
import entities.core.TextilePrinting;
import wrappers.ArticleFilterWrapper;
import wrappers.EmbroideryFilterWrapper;
import wrappers.ProductFilterWrapper;
import wrappers.TextilePritingFilterWrapper;

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
    
    @Test
    public void testFindArticleByFilterNormal() {
        ArticleFilterWrapper articleFront= new ArticleFilterWrapper ();
        articleFront.setDescription("");
        articleFront.setReference("");
        articleFront.setMaxRetailPrice(new BigDecimal("0"));
        articleFront.setMinRetailPrice(new BigDecimal("0"));
        articleFront.setMaxWholesalePrice(new BigDecimal("0"));
        articleFront.setMinWholesalePrice(new BigDecimal("1"));
        articleFront.setStock(0);
        
        assertNotNull(articleDao.findArticlesByFilter(articleFront));
    }
    
    
    @Test
    public void testFindArticleByFilterExtremWholeSalePrice() {
        ArticleFilterWrapper articleFront= new ArticleFilterWrapper ();
        articleFront.setDescription("");
        articleFront.setReference("");
        articleFront.setMaxRetailPrice(new BigDecimal("0"));
        articleFront.setMinRetailPrice(new BigDecimal("0"));
        articleFront.setMaxWholesalePrice(new BigDecimal("0"));
        articleFront.setMinWholesalePrice(new BigDecimal("10000000000000"));
        articleFront.setStock(0);
        assertEquals(0,articleDao.findArticlesByFilter(articleFront).size());
        articleFront.setMaxWholesalePrice(new BigDecimal("1"));
        articleFront.setMinWholesalePrice(new BigDecimal("10"));
        assertEquals(0,articleDao.findArticlesByFilter(articleFront).size());
    }
    
    @Test
    public void testFindTextilePrintingByFilterNormal() {
        TextilePrinting textilePrinting=textilePrintingDao.findAll().get(0);
        TextilePritingFilterWrapper textilePrintingFront= new TextilePritingFilterWrapper ();
        textilePrintingFront.setDescription(textilePrinting.getDescription());
        textilePrintingFront.setReference("");
        textilePrintingFront.setMaxRetailPrice(new BigDecimal("0"));
        textilePrintingFront.setMinRetailPrice(new BigDecimal("1"));
        textilePrintingFront.setType("");        
        assertNotNull(textilePrintingDao.findTextilePrintingsByFilter(textilePrintingFront));
    }
    
    
    @Test
    public void testFindEmbroideryByFilterNormal() {
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
        assertNotNull(embroideryDao.findEmbroideryByFilter(embroideryFront));
    }
    
    @Test
    public void testFindEmbroideryByFilterExrem() {
        EmbroideryFilterWrapper embroideryFront= new EmbroideryFilterWrapper ();
        embroideryFront.setDescription("");
        embroideryFront.setReference("");
        embroideryFront.setMaxRetailPrice(new BigDecimal("0"));
        embroideryFront.setMinRetailPrice(new BigDecimal("0"));
        embroideryFront.setMaxColors(0);
        embroideryFront.setMinColors(1000000);
        assertEquals(0,embroideryDao.findEmbroideryByFilter(embroideryFront).size());
        embroideryFront.setMaxColors(1);
        embroideryFront.setMinColors(10);
        assertEquals(0,embroideryDao.findEmbroideryByFilter(embroideryFront).size());
        embroideryFront.setMaxSquareMillimeters(0);
        embroideryFront.setMinSquareMillimeters(1000000000);
        assertEquals(0,embroideryDao.findEmbroideryByFilter(embroideryFront).size());
        embroideryFront.setMaxSquareMillimeters(1);
        embroideryFront.setMinSquareMillimeters(10);
        assertEquals(0,embroideryDao.findEmbroideryByFilter(embroideryFront).size());
        embroideryFront.setMaxStitches(0);
        embroideryFront.setMinStitches(1000000000);
        assertEquals(0,embroideryDao.findEmbroideryByFilter(embroideryFront).size());   
        embroideryFront.setMaxStitches(1);
        embroideryFront.setMinStitches(10);
        assertEquals(0,embroideryDao.findEmbroideryByFilter(embroideryFront).size());
    }

}
