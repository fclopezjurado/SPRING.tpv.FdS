package controllers;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.TokenWrapper;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
public class TokenControllerIT {

    @Autowired
    private TokenController tokenController;

    @Test
    public void testLogin() {
        TokenWrapper tokenWrapper = tokenController.login(666000000);
        assertTrue(tokenWrapper.getToken().length() > 20);
        assertTrue(tokenWrapper.getRol().equals("CUSTOMER"));
    }

}
