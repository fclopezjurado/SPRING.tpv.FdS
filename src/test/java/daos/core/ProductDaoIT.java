package daos.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.AlarmType;

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
        assertEquals(false, articleDao.findArticlesWithAlarmActive(AlarmType.WARNING).isEmpty());
    }
    
    @Test
    public void testFindArticlesOfProvidersWithAlarmActive() {
        assertEquals(false, articleDao.findArticlesOfOneProviderWithAlarmActive(providerDao.findAll().get(0)).isEmpty());
    }

}
