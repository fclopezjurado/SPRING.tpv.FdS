package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketReferenceException;
import controllers.InvoiceController;
import controllers.TicketController;
import wrappers.InvoiceWrapper;
import wrappers.TicketWrapper;

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
    public InvoiceWrapper createInvoice(@RequestBody TicketWrapper ticketWrapper) throws NotFoundTicketReferenceException {
        if (!this.ticketController.ticketExistsByReference(ticketWrapper.getReference()))
            throw new NotFoundTicketReferenceException();

        return this.invoiceController.createInvoice(ticketWrapper.getReference());
    }

}
