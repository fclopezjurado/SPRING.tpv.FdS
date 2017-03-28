package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import daos.users.UserDao;
import entities.core.Budget;
import entities.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class BudgetDaoIT {

    @Autowired
    private BudgetDao budgetDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testCreate() {
        assertEquals(3, budgetDao.count());
    }

    @Test
    public void testFindByReference() {
        Budget budgetRetrieved = budgetDao.findByReference(budgetDao.findOne(1L).getReference());
        assertEquals(budgetRetrieved.getId(), budgetDao.findOne(1L).getId());
    }

    @Test
    public void testFindByUserMobile() {
        User user = userDao.findByMobile(666000000);
        assertEquals(user.getMobile(), budgetDao.findByUserMobile(user.getMobile()).get(0).getUser().getMobile());
    }

}
