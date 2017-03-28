package api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import entities.core.Ticket;
import entities.core.TicketState;
import wrappers.CashierBalanceWrapper;
import wrappers.InvoicesWrapper;
import wrappers.TicketWrapper;
import wrappers.TicketsWrapper;

public class TicketResourceFunctionalTesting {

    public static final String URL = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api" + Uris.VERSION;

    private static final long adminMobile = 123456789;

    private static final long wrongMobile = 666666666;

    private static final String adminEmail = "admin@gmail.com";

    private static final String wrongEmail = "test@test.es";

    private static final int wrongInvoiceID = 999;

    @Autowired
    private Ticket ticket;

    TicketWrapper ticketWrapper;

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
        ticket = new Ticket(69L, TicketState.COMMITTED);
        ticketWrapper = new TicketWrapper(ticket);
    }

    @Test
    public void testGetTicket() {
        TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketsWrapper.class).get()
                .build();
        assertTrue(tickets.getFirstTicket().getReference().length() > 20);
    }

    @Test
    public void testupdateTickets() {
        TicketWrapper ticketCreated = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketWrapper.class)
                .body(ticketWrapper).post().build();

        ticketCreated.setTicketState(TicketState.OPENED);
        new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketWrapper.class).body(ticketCreated).put().build();

        TicketWrapper ticketObtained = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS)
                .path("/" + ticketCreated.getReference()).clazz(TicketWrapper.class).get().build();

        assertEquals(TicketState.OPENED, ticketObtained.getTicketState());
    }

    @Test
    public void testCreateAndGetTicketbyReference() {
        TicketWrapper ticketCreated = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketWrapper.class)
                .body(ticketWrapper).post().build();

        TicketWrapper ticketObtained = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS)
                .path("/" + ticketCreated.getReference()).clazz(TicketWrapper.class).get().build();

        assertEquals(ticketCreated.getId(), ticketObtained.getId());
    }

    @Test
    public void testGetTicketByReferenceNotCommitted() {
        TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketsWrapper.class).get()
                .build();
        TicketWrapper ticket = tickets.getTickets().stream().filter(t -> t.getTicketState() != TicketState.COMMITTED).findFirst()
                .orElse(null);
        assertNotNull(ticket);
        ticket = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).param("reference", ticket.getReference())
                .clazz(TicketWrapper.class).get().build();
        assertTrue(ticket.getReference().length() > 20);
    }

    @Test
    public void testGetTicketByReferenceCommitted() {
        try {
            TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketsWrapper.class).get()
                    .build();
            TicketWrapper ticket = tickets.getTickets().stream().filter(t -> t.getTicketState() == TicketState.COMMITTED).findFirst()
                    .orElse(null);
            assertNotNull(ticket);
            new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).param("reference", ticket.getReference())
                    .clazz(TicketWrapper.class).get().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info(
                    "testGetTicketByReferenceCommitted (" + httpError.getMessage() + "):\n     " + httpError.getResponseBodyAsString());
        }
    }

    @Test
    public void testGetTicketByReferenceWithInvalidReference() {
        try {
            new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).param("reference", "Invalid Reference")
                    .clazz(TicketWrapper.class).get().build();
            fail();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
            LogManager.getLogger(this.getClass()).info("testGetTicketByReferenceWithInvalidReference (" + httpError.getMessage()
                    + "):\n     " + httpError.getResponseBodyAsString());
        }
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

    @Test
    public void testGetTicketByInvoiceID() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator mobile number from properties file. long adminMobile
         * = Long.valueOf(environment.getProperty("admin.mobile"));
         */
        InvoicesWrapper invoices = new RestBuilder<InvoicesWrapper>(RestService.URL).path(Uris.INVOICES).path("/" + adminMobile)
                .clazz(InvoicesWrapper.class).get().build();
        assertEquals(false, invoices.isEmpty());

        TicketWrapper ticket = new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).path(Uris.INVOICE)
                .path("/" + invoices.getFirstInvoice().getId()).clazz(TicketWrapper.class).get().build();
        assertEquals(adminMobile, ticket.getUser().getMobile());
    }

    @Test
    public void testGetTicketByInvoiceIDException() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator mobile number from properties file. long adminMobile
         * = Long.valueOf(environment.getProperty("admin.mobile"));
         *
         * InvoicesWrapper invoices = new RestBuilder<InvoicesWrapper>(RestService.URL).path(Uris.INVOICES).path("/" +
         * adminMobile).clazz(InvoicesWrapper.class).get().build(); assertEquals(false, invoices.isEmpty());
         */
        try {
            new RestBuilder<TicketWrapper>(RestService.URL).path(Uris.TICKETS).path(Uris.INVOICE).path("/" + wrongInvoiceID)
                    .clazz(TicketWrapper.class).get().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
        }
    }

    @Test
    public void testGetTotalSoldByDay(){
        SimpleDateFormat formatter = new SimpleDateFormat(CashierBalanceWrapper.dateFormat);
        Calendar day = Calendar.getInstance();
        BigDecimal total = new RestBuilder<BigDecimal>(RestService.URL).path(Uris.TICKETS).path(Uris.CASHIER_BALANCE).param("day",formatter.format(day.getTime())).get()
            .clazz(BigDecimal.class).build();
        
        assertEquals(new BigDecimal(620).stripTrailingZeros(), total.stripTrailingZeros());
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
