package controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.users.Role;
import wrappers.UserWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class UserControllerIT {

    private static final long USER_MOBILE = 666666666;

    private static final String USER_NAME = "username";

    private static final String USER_PASSWORD = "password";

    @Autowired
    private UserController userController;

    @Autowired
    private Environment environment;

    @Test
    public void testRegistration() {
        UserWrapper user = new UserWrapper(USER_MOBILE, USER_NAME, USER_PASSWORD);
        assertTrue(userController.registration(user, Role.CUSTOMER));
    }

    @Test
    public void testRegistrationWrong() {
        UserWrapper user = new UserWrapper(Long.valueOf(environment.getProperty("admin.mobile")), environment.getProperty("admin.username"),
                environment.getProperty("admin.password"));
        assertFalse(userController.registration(user, Role.CUSTOMER));
    }

    @Test
    public void testUserExistsByMobile() {
        assertTrue(userController.userExistsByMobile(Long.valueOf(environment.getProperty("admin.mobile"))));
    }

    @Test
    public void testUserExistsByMobileWrong() {
        assertFalse(userController.userExistsByMobile(USER_MOBILE));
    }
}
