package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
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
import daos.users.UserDao;
import entities.core.Product;
import entities.core.Shopping;
import entities.core.Ticket;
import entities.core.TicketState;
import entities.users.User;
import wrappers.TicketWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class TicketControllerIT {

    @Autowired
    private TicketController ticketController;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserDao userDao;

    Ticket ticket;

    User user;

    @Before
    public void setup() {
        ticket = ticketDao.findById(1L);
        user = userDao.findByMobile(666000000);
        LogManager.getLogger(this.getClass()).info(ticket.getReference());
    }

    @Test
    public void testGetTicketByReference() {
        TicketWrapper ticketWrapper = ticketController.getTicketByReference(ticket.getReference());

        assertTrue(ticketWrapper.getReference().length() > 20);
        assertEquals(ticketWrapper.getTicketState(), TicketState.CLOSED);
    }

    @Test
    public void testCreateCommitedTicket() {
        List<Shopping> shoppingList = new ArrayList<>();
        TicketWrapper ticketWrapper;
        for (int i = 0; i < 2; i++) {
            Product product = articleDao.findOne(84000001111L + i);
            shoppingList.add(new Shopping(2 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticketWrapper = ticketController.createTicket(shoppingList, null);

        assertEquals(ticketWrapper.getTicketState(), TicketState.COMMITTED);
    }

    @Test
    public void testCreateOpenedTicket() {
        List<Shopping> shoppingList = new ArrayList<>();
        TicketWrapper ticketWrapper;
        for (int i = 0; i < 2; i++) {
            Product product = articleDao.findOne(84000001111L + i);
            shoppingList.add(new Shopping(3 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticketWrapper = ticketController.createTicket(shoppingList, user);

        assertEquals(ticketWrapper.getTicketState(), TicketState.OPENED);
    }

    @Test
    public void testCreateAndUpdateTicket() {
        List<Shopping> shoppingList = new ArrayList<>();
        TicketWrapper ticketWrapper;
        for (int i = 0; i < 3; i++) {
            Product product = articleDao.findOne(84000001111L + i);
            shoppingList.add(new Shopping(4 + i, 0, product.getId(), product.getDescription(), product.getRetailPrice()));
        }
        ticketWrapper = ticketController.createTicket(shoppingList, user);

        assertEquals(ticketWrapper.getTicketState(), TicketState.OPENED);

        ticketWrapper.setTicketState(TicketState.STARTED);
        ticketWrapper.getShoppingList().remove(0);

        ticketWrapper = ticketController.updateTicket(ticketWrapper);

        assertEquals(ticketWrapper.getTicketState(), TicketState.STARTED);
        assertEquals(2, ticketWrapper.getShoppingList().size());
    }
}
