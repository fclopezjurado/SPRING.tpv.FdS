package controllers;

import daos.core.InvoiceDao;
import daos.core.TicketDao;
import entities.core.Invoice;
import entities.core.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import api.exceptions.NotFoundInvoiceIdException;
import api.exceptions.NotFoundTicketReferenceException;
import api.exceptions.NotFoundUserMobileException;
import wrappers.InvoiceWrapper;
import wrappers.InvoicesWrapper;

import java.util.List;

@Controller
public class InvoiceController {

    private static final int FIRST_INVOICE_ID = 1;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public InvoiceWrapper createInvoice(String ticketReference) throws NotFoundTicketReferenceException {
        int newInvoiceID = this.invoiceDao.findMaxInvoiceID();
        Ticket ticket = this.ticketDao.findByReference(ticketReference);

        if (ticket == null)
            throw new NotFoundTicketReferenceException();

        if (newInvoiceID < FIRST_INVOICE_ID)
            newInvoiceID = FIRST_INVOICE_ID;
        else
            newInvoiceID++;

        Invoice invoice = new Invoice(newInvoiceID, ticket);
        this.invoiceDao.save(invoice);

        return new InvoiceWrapper(newInvoiceID, ticket.getReference());
    }

    public boolean invoiceExists(int id) throws NotFoundInvoiceIdException {
        Invoice invoice = invoiceDao.findOne(id);

        if (invoice == null)
            throw new NotFoundInvoiceIdException();

        return invoice.getId() == id;
    }

    public InvoicesWrapper getInvoicesByUserMobile(long userMobile) throws NotFoundUserMobileException {
        List<Invoice> invoices = invoiceDao.findByUserMobile(userMobile);

        if (invoices == null)
            throw new NotFoundUserMobileException();

        InvoicesWrapper invoicesWrapper = new InvoicesWrapper();

        for (Invoice invoice : invoices)
            invoicesWrapper.addInvoiceWrapper(new InvoiceWrapper(invoice.getId(), invoice.getTicket().getReference()));

        return invoicesWrapper;
    }

}
