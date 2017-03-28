package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.exceptions.NotFoundTicketReferenceException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import entities.users.Role;
import wrappers.TicketsWrapper;
import wrappers.UserWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class UserControllerIT {

    private static final long USER_MOBILE = 666666666;

    private static final String USER_NAME = "username";

    private static final String USER_PASSWORD = "password";

    private static final String USER_EMAIL = "test@test.com";

    private static final String INVALID_TICKET_REFERENCE = "wg_yXs1LJmSvNsld-aXBg27P1jA";

    @Autowired
    private UserController userController;

    @Autowired
    private TicketController ticketController;

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

    @Test
    public void testUserExistsByEmail() {
        assertTrue(userController.userExistsByEmail(environment.getProperty("admin.email")));
    }

    @Test
    public void testUserExistsByEmailWrong() {
        assertFalse(userController.userExistsByEmail(USER_EMAIL));
    }

    @Test
    public void testGetByTicketReference() {
        TicketsWrapper ticketsWrapper = this.ticketController.findAll();

        try {
            UserWrapper user = this.userController.getByTicketReference(ticketsWrapper.getFirstTicket().getReference());
            assertEquals(ticketsWrapper.getFirstTicket().getUser().getMobile(), user.getMobile());
        } catch (NotFoundTicketReferenceException exception) {
            LogManager.getLogger(this.getClass()).info("testGetByTicketReference was wrong:  " + exception.getMessage());
            fail();
        }
    }

    @Test
    public void testGetByTicketReferenceWithWrongTicketReference() {
        try {
            this.userController.getByTicketReference(INVALID_TICKET_REFERENCE);
            fail();
        } catch (NotFoundTicketReferenceException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testFindAll() {
        assertFalse(this.userController.findAll().isEmpty());
    }

}
