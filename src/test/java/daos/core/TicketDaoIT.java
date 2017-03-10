package daos.core;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsPersistenceConfig;
import entities.core.Ticket;
import entities.core.TicketState;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class})
public class TicketDaoIT {

	private Ticket ticketEntity;
	
    @Autowired
    private TicketDao ticketDao;
    
    @Before
    public void seeder(){
        ticketEntity = new Ticket(1,TicketState.STARTED);
        ticketDao.save(ticketEntity);
    }
    
    @Test
    public void testCreate() {
        assertEquals(3, ticketDao.count());
    }

    @Test 
    public void testFindByReference(){
        Ticket ticketRetrieved = ticketDao.findByReference(ticketEntity.getReference());
        assertEquals(ticketRetrieved.getId(),ticketEntity.getId());
    }
}
