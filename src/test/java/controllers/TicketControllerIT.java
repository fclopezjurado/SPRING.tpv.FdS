package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import daos.core.ArticleDao;
import daos.core.TicketDao;
import entities.core.Product;
import entities.core.Shopping;
import entities.core.Ticket;
import entities.core.TicketState;
import services.TicketService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class TicketControllerIT {

    @Autowired
    private TicketService ticketService;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    TicketDao ticketDao;

    private Ticket ticket;

    @Before
    public void before() {

        ticket = new Ticket(ticketService.getFirstTicketOfDay(), TicketState.CLOSED);
        for (int i = 0; i < 4; i++) {
            Product product = articleDao.findOne(84000001111L + i);
            ticket.addShopping(new Shopping(1 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticketDao.save(ticket);
    }

    @Test
    public void testGetNextTicketId() {
        assertEquals(ticketService.getFirstTicketOfDay() + 1, ticketService.getNextTicketId());
    }

    @After
    public void deleteSampleTicket() {
        ticketDao.delete(ticket);
    }
}
