package controllers;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import api.exceptions.NotFoundTicketReferenceException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsPersistenceConfig;
import wrappers.InvoiceWrapper;
import wrappers.TicketsWrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class})
public class InvoiceControllerIT {

    private static final String WRONG_TICKET_REFERENCE = "wg_yXs1LJmSvNsld-aXBg27P1jA";

    @Autowired
    private InvoiceController invoiceController;

    @Autowired
    private TicketController ticketController;

    @Test
    public void testCreateInvoice() {
        TicketsWrapper ticketsWrapper = this.ticketController.findAll();

        try {
            InvoiceWrapper invoiceWrapper = this.invoiceController.createInvoice(ticketsWrapper.getFirstTicket().getReference());
            assertEquals(ticketsWrapper.getFirstTicket().getReference(), invoiceWrapper.getTicketReference());
        } catch (NotFoundTicketReferenceException exception) {
            LogManager.getLogger(this.getClass()).info("testCreateInvoice was wrong:  " + exception.getMessage());
            fail();
        }
    }

    @Test
    public void testCreateInvoiceWithWrongTicketReference() {
        try {
            this.invoiceController.createInvoice(WRONG_TICKET_REFERENCE);
            fail();
        } catch (NotFoundTicketReferenceException exception) {
            assertTrue(true);
        }
    }

}
