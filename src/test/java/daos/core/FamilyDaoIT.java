package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class FamilyDaoIT {
    
    @Autowired
    private FamilyDaoImpl familyDaoImpl;
    
    @Test
    public void testFindAll(){
        assertEquals(3, familyDaoImpl.findAllFamilies().size());
    }
    
    @Test
    public void testFindProductByFamilyName(){
        assertEquals(4, familyDaoImpl.findProductByFamilyName("familyName1").size());
        assertEquals(8, familyDaoImpl.findProductByFamilyName("familyName2").size());
        assertEquals(12, familyDaoImpl.findProductByFamilyName("familyName3").size());
    }
}
