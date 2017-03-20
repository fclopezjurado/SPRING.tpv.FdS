package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.InvoiceDao;
import daos.core.TicketDao;
import entities.core.Invoice;
import entities.core.Ticket;
import wrappers.InvoiceWrapper;
import wrappers.InvoicesWrapper;

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

    public InvoiceWrapper createInvoice(String ticketReference) {
        Ticket ticket = this.ticketDao.findByReference(ticketReference);
        int newInvoiceID = this.invoiceDao.findMaxInvoiceID();

        if (newInvoiceID < FIRST_INVOICE_ID)
            newInvoiceID = FIRST_INVOICE_ID;
        else
            newInvoiceID++;

        Invoice invoice = new Invoice(newInvoiceID, ticket);
        this.invoiceDao.save(invoice);

        return new InvoiceWrapper(newInvoiceID, ticket.getId());
    }

    public boolean invoiceExists(int id) {
        Invoice invoice = invoiceDao.findOne(id);
        return invoice.getId() == id;
    }

    public InvoicesWrapper getInvoicesByUserMobile(long userMobile) {
        List<Invoice> invoices = invoiceDao.findByUserMobile(userMobile);
        InvoicesWrapper invoicesWrapper = new InvoicesWrapper();

        for (Invoice invoice : invoices)
            invoicesWrapper.addInvoiceWrapper(new InvoiceWrapper(invoice.getId(), invoice.getTicket().getId()));

        return invoicesWrapper;
    }

}
