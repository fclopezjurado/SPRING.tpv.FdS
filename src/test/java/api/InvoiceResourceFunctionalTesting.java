package api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import wrappers.InvoiceWrapper;
import wrappers.TicketWrapper;

public class InvoiceResourceFunctionalTesting {

    public static final String ticketReferenceWrong = "DW7yzcVpRLguAs";

    @Before
    public void seedDataBase() {
        new RestService().deleteAll();
        new RestService().seedDatabase();
    }

    @Test
    public void testCreateInvoice() {
        List<TicketWrapper> tickets = Arrays
                .asList(new RestBuilder<TicketWrapper[]>(RestService.URL).path(Uris.TICKETS).clazz(TicketWrapper[].class).get().build());
        InvoiceWrapper invoice = new RestBuilder<InvoiceWrapper>(RestService.URL).path(Uris.INVOICES).body(tickets.get(0))
                .clazz(InvoiceWrapper.class).post().build();

        assertEquals(invoice.getTicket_id(), tickets.get(0).getId());
    }

    @Test
    public void testCreateInvoiceException() {
        TicketWrapper ticketForRequest = new TicketWrapper();
        ticketForRequest.setReference(ticketReferenceWrong);

        try {
            new RestBuilder<InvoiceWrapper>(RestService.URL).path(Uris.INVOICES).body(ticketForRequest).clazz(InvoiceWrapper.class).post()
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
