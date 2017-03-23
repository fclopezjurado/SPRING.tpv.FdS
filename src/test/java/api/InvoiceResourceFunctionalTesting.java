package api;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import wrappers.InvoiceWrapper;
import wrappers.InvoicesWrapper;
import wrappers.TicketsWrapper;
import wrappers.UserWrapper;

import static org.junit.Assert.assertEquals;

public class InvoiceResourceFunctionalTesting {

    private static final String ticketReferenceWrong = "DW7yzcVpRLguAs";

    private static final long adminMobile = 123456789;

    private static final long wrongMobile = 666666666;

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void testCreateInvoice() {
        TicketsWrapper tickets = new RestBuilder<TicketsWrapper>(RestService.URL).path(Uris.TICKETS).clazz(TicketsWrapper.class).get()
                .build();
        InvoiceWrapper invoice = new RestBuilder<InvoiceWrapper>(RestService.URL).path(Uris.INVOICES)
                .param("reference", tickets.getFirstTicket().getReference()).clazz(InvoiceWrapper.class).post().build();

        assertEquals(invoice.getTicketReference(), tickets.getFirstTicket().getReference());
    }

    @Test
    public void testCreateInvoiceException() {
        try {
            new RestBuilder<InvoiceWrapper>(RestService.URL).path(Uris.INVOICES).param("reference", ticketReferenceWrong)
                    .clazz(InvoiceWrapper.class).post().build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
        }
    }

    @Test
    public void testGetInvoicesByUserMobile() {
        /**
         * TODO: Use of "org.springframework.core.env.Environment" to get administrator mobile number from properties file. long adminMobile
         * = Long.valueOf(environment.getProperty("admin.mobile"));
         */
        InvoicesWrapper invoices = new RestBuilder<InvoicesWrapper>(RestService.URL).path(Uris.INVOICES).path("/" + adminMobile)
                .clazz(InvoicesWrapper.class).get().build();

        assertEquals(false, invoices.isEmpty());

        String ticketReference = invoices.getFirstInvoice().getTicketReference();
        UserWrapper user = new RestBuilder<UserWrapper>(RestService.URL).path(Uris.USERS).path("/" + ticketReference)
                .clazz(UserWrapper.class).get().build();

        assertEquals(adminMobile, user.getMobile());
    }

    @Test
    public void testGetInvoicesByUserMobileException() {
        try {
            new RestBuilder<InvoicesWrapper>(RestService.URL).path(Uris.INVOICES).path("/" + wrongMobile).clazz(InvoicesWrapper.class).get()
                    .build();
        } catch (HttpClientErrorException httpError) {
            assertEquals(HttpStatus.NOT_FOUND, httpError.getStatusCode());
        }
    }

    @After
    public void after() {
        new RestService().deleteAll();
    }

}
