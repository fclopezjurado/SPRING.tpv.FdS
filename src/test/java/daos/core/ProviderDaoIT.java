package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import entities.core.Provider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class ProviderDaoIT {

    @Autowired
    private ProviderDao providerDao;

    @Test
    public void testCreate() {
        assertEquals(4, providerDao.count());
    }

    @Test
    public void testFindByMobile() {
    	Provider provider = providerDao.findByMobile(666100000);
        assertEquals(666100000,  provider.getMobile());
    }
    
    @Test
    public void testFindById() {
    	Provider provider = providerDao.findById(2);
        assertEquals(2,  provider.getId());
    }
}
