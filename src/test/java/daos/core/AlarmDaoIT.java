package daos.core;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class AlarmDaoIT {

    @Autowired
    private AlarmDao alarmDao;

    @Test
    public void testCreateAlarm() {
        assertEquals(2, alarmDao.count());
    }
    
    @Test 
    public void testFindById() {
        assertEquals(1, alarmDao.findById(1).getId());
        assertNull(alarmDao.findById(2423));
    }

}
