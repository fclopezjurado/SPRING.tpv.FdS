package controllers;

import api.exceptions.NotFoundInvoiceIdException;
import api.exceptions.NotFoundTicketReferenceException;
import api.exceptions.NotFoundUserMobileException;
import config.PersistenceConfig;
import config.TestsControllerConfig;
import config.TestsMailConfig;
import config.TestsPersistenceConfig;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wrappers.InvoiceWrapper;
import wrappers.InvoicesWrapper;
import wrappers.TicketsWrapper;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, TestsPersistenceConfig.class, TestsControllerConfig.class, TestsMailConfig.class})
public class InvoiceControllerIT {

    private static final String INVALID_TICKET_REFERENCE = "wg_yXs1LJmSvNsld-aXBg27P1jA";

    private static final long VALID_USER_MOBILE = 666000000;

    private static final long INVALID_USER_MOBILE = 666666666;

    private static final int INVALID_INVOICE_ID = 999;

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
            this.invoiceController.createInvoice(INVALID_TICKET_REFERENCE);
            fail();
        } catch (NotFoundTicketReferenceException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testInvoiceExists() {
        try {
            InvoicesWrapper invoices = this.invoiceController.getInvoicesByUserMobile(VALID_USER_MOBILE);
            assertEquals(false, invoices.isEmpty());
            assertTrue(this.invoiceController.invoiceExists(invoices.getFirstInvoice().getId()));
        } catch (NotFoundInvoiceIdException exception) {
            LogManager.getLogger(this.getClass()).info("testInvoiceExists was wrong:  " + exception.getMessage());
            fail();
        } catch (NotFoundUserMobileException exception) {
            LogManager.getLogger(this.getClass()).info("testInvoiceExists was wrong:  " + exception.getMessage());
            fail();
        }
    }

    @Test
    public void testInvoiceExistsException() {
        try {
            this.invoiceController.invoiceExists(INVALID_INVOICE_ID);
        } catch (NotFoundInvoiceIdException exception) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetInvoicesByUserMobile() {
        try {
            InvoicesWrapper invoices = this.invoiceController.getInvoicesByUserMobile(VALID_USER_MOBILE);
            assertEquals(false, invoices.isEmpty());
        } catch (NotFoundUserMobileException exception) {
            LogManager.getLogger(this.getClass()).info("testInvoiceExists was wrong:  " + exception.getMessage());
            fail();
        }
    }

    @Test
    public void testGetInvoicesByUserMobileException() {
        try {
            this.invoiceController.getInvoicesByUserMobile(INVALID_USER_MOBILE);
        } catch (NotFoundUserMobileException exception) {
            assertTrue(true);
        }
    }

}
