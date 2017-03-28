package daos.users;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import entities.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class TokenDaoIT {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenDao tokenDao;

    @Test
    public void testFindByUser() {
        User user = userDao.findByMobile(666000000);
        assertNotNull(tokenDao.findByUser(user));
        assertNull(tokenDao.findByUser(userDao.findByMobile(666000001)));
    }

}
