package daos.users;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import entities.users.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class AuthorizationDaoIT {

    @Autowired
    private AuthorizationDao authorizationDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindRoleByUser() {
        List<Role> roles = authorizationDao.findRoleByUser(userDao.findByMobile(666000000));
        assertEquals(1, roles.size());
        assertEquals(Role.CUSTOMER, roles.get(0));
    }

}
