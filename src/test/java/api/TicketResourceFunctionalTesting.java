package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.core.Ticket;
import entities.core.TicketState;
import wrappers.TicketWrapper;

public class TicketResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();

    }

    @Test
    public void testGetTicket() {
        List<TicketWrapper> tickets = Arrays
                .asList(new RestBuilder<TicketWrapper[]>(RestService.URL).path(Uris.TICKETS).clazz(TicketWrapper[].class).get().build());
        assertTrue(tickets.get(0).getReference().length() > 20);

    }

    @Test
    public void testCreateTicket() {
        Ticket ticket = new Ticket(69L, TicketState.COMMITTED);
        TicketWrapper ticketWrapper = new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                ticket.getShoppingList(), ticket.getUser());

        TicketWrapper newTicketWrapper = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketWrapper.class)
                .body(ticketWrapper).post().build();
        assertEquals(TicketState.COMMITTED, newTicketWrapper.getTicketState());

    }

    @After
    public void deleteAll() {
        new RestService().deleteAll();
    }

}
