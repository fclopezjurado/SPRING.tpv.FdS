package daos.core;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class FamilyDaoIT {

    @Autowired
    private FamilyDao familyDao;

    @Test
    public void testCreate() {
        assertEquals(1, familyDao.count());
    }
}
