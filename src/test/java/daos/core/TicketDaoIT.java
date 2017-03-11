package daos.core;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Ticket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TicketDaoIT {

    @Autowired
    private TicketDao ticketDao;

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

}
