package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.core.Ticket;
import entities.core.TicketState;
import wrappers.TicketWrapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.TicketsWrapper;

public class TicketResourceFunctionalTesting {

    private static final long adminMobile = 123456789;

    private static final long wrongMobile = 666666666;

    private static final String adminEmail = "admin@gmail.com";

    private static final String wrongEmail = "test@test.es";

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void testGetTicket() {
        TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketsWrapper.class).get()
                .build();
        assertTrue(tickets.getFirstTicket().getReference().length() > 20);
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

    @Test
    public void testGetTicketsByUserMobile() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator mobile number from properties file. long adminMobile
         * = Long.valueOf(environment.getProperty("admin.mobile"));
         */
        TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).path(Uris.USER_MOBILE_PATH)
                .path("/" + adminMobile).clazz(TicketsWrapper.class).get().build();
        assertTrue(tickets.userHasTicketsByMobile(adminMobile));
    }

    @Test
    public void testGetTicketsByUserMobileException() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator mobile number from properties file. long adminMobile
         * = Long.valueOf(environment.getProperty("admin.mobile"));
         */
        try {
            new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).path(Uris.USER_MOBILE_PATH).path("/" + wrongMobile)
                    .clazz(TicketsWrapper.class).get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
        }
    }

    @Test
    public void testGetTicketsByUserEmail() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator email account from properties file. String
         * adminEmail = environment.getProperty("admin.email");
         */
        TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).path(Uris.USER_EMAIL_PATH)
                .param("email", adminEmail).clazz(TicketsWrapper.class).get().build();
        assertTrue(tickets.userHasTicketsByEmail(adminEmail));
    }

    @Test
    public void testGetTicketsByUserEmailException() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator email account from properties file. String
         * adminEmail = environment.getProperty("admin.email");
         */
        try {
            new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).path(Uris.USER_EMAIL_PATH).param("email", wrongEmail)
                    .clazz(TicketsWrapper.class).get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
        }
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
