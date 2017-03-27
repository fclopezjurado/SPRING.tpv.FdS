package daos.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import daos.users.UserDao;
import entities.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})

public class InvoiceDaoIT {

    @Autowired
    private InvoiceDao invoiceDao;
    
    @Autowired
    private UserDao userDao;

    @Test
    public void testCreate() {
        assertEquals(2, invoiceDao.count());
    }
    
    @Test
    public void testFindByUserMobile() {
        User user = userDao.findByMobile(666000000);
        assertEquals(user.getMobile(), invoiceDao.findByUserMobile(user.getMobile()).get(0).getTicket().getUser().getMobile());
    }

}
