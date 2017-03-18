package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketReferenceException;
import controllers.InvoiceController;
import controllers.TicketController;
import wrappers.InvoiceWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.INVOICES)
public class InvoiceResource {

    private InvoiceController invoiceController;

    private TicketController ticketController;

    @Autowired
    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    @Autowired
    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createInvoice(@RequestParam(value = "reference", required = true) String ticketReference)
            throws NotFoundTicketReferenceException {
        if (!this.ticketController.ticketExistsByReference(ticketReference))
            throw new NotFoundTicketReferenceException();

        this.invoiceController.createInvoice(ticketReference);
    }

    @RequestMapping(method = RequestMethod.GET)
    public InvoiceWrapper getInvoiceByTicketReference(@RequestParam(value = "reference", required = true) String ticketReference)
            throws NotFoundTicketReferenceException {
        if (!this.ticketController.ticketExistsByReference(ticketReference))
            throw new NotFoundTicketReferenceException();

        return this.invoiceController.findByTicketReference(ticketReference);
    }

}
