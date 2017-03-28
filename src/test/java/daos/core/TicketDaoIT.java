package daos.core;

import config.PersistenceConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import daos.users.UserDao;
import entities.core.Invoice;
import entities.core.Ticket;
import entities.users.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsMailConfig.class})
public class TicketDaoIT {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    public void testCreate() {
        assertEquals(3, ticketDao.count());
    }

    @Test
    public void testFindByReference() {
        Ticket ticketRetrieved = ticketDao.findByReference(ticketDao.findOne(1L).getReference());
        assertEquals(ticketRetrieved.getId(), ticketDao.findOne(1L).getId());
    }

    @Test
    public void testCountTicketsBetweenDates() {

        Calendar dateInicio = Calendar.getInstance();
        int diaBase = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaBase - 1);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, diaBase + 5);

        int counter = ticketDao.countTicketsBetweenDates(dateInicio, dateFin);

        assertEquals(3, counter);
    }

    @Test
    public void testFindByUserEmail() {
        User user = userDao.findByMobile(666000000);
        assertEquals(user.getEmail(), ticketDao.findByUserEmail(user.getEmail()).get(0).getUser().getEmail());
    }

    @Test
    public void testFindByUserMobile() {
        User user = userDao.findByMobile(666000000);
        assertEquals(user.getMobile(), ticketDao.findByUserMobile(user.getMobile()).get(0).getUser().getMobile());
    }

    @Test
    public void testFindByInvoiceID() {
        Invoice invoice = invoiceDao.findOne(1);
        assertEquals(invoice.getTicket().getReference(), ticketDao.findByInvoiceID(invoice.getId()).getReference());
    }
    
    @Test
    public void testGetTotalPriceOfTicketsBetweenDates(){
        Calendar dateInicio = Calendar.getInstance();
        int diaBase = dateInicio.get(Calendar.DAY_OF_MONTH);
        dateInicio.set(Calendar.DAY_OF_MONTH, diaBase - 1);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(Calendar.DAY_OF_MONTH, diaBase + 5);
        
        assertNotNull(ticketDao.getTotalPriceOfTicketsBetweenDates(dateInicio, dateFin));
        
        
    }

}
