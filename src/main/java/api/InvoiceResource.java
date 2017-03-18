package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketIdException;
import controllers.InvoiceController;
import controllers.TicketController;

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

    @RequestMapping(value = Uris.ID, method = RequestMethod.POST)
    public void createInvoice(@PathVariable(value = "id") long ticketID) throws NotFoundTicketIdException {
        if (!this.ticketController.ticketExists(ticketID))
            throw new NotFoundTicketIdException();

        this.invoiceController.createInvoice(ticketID);
    }

}
