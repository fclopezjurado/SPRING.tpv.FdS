package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundTicketReferenceException;
import api.exceptions.NotFoundUserMobileException;
import controllers.InvoiceController;
import controllers.TicketController;
import controllers.UserController;
import wrappers.InvoiceWrapper;
import wrappers.InvoicesWrapper;
import wrappers.TicketWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class InvoiceResource {

    private InvoiceController invoiceController;

    private TicketController ticketController;

    private UserController userController;

    @Autowired
    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    @Autowired
    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(value = Uris.INVOICES, method = RequestMethod.POST)
    public InvoiceWrapper createInvoice(@RequestBody TicketWrapper ticketWrapper) throws NotFoundTicketReferenceException {
        if (!this.ticketController.ticketExistsByReference(ticketWrapper.getReference()))
            throw new NotFoundTicketReferenceException();

        return this.invoiceController.createInvoice(ticketWrapper.getReference());
    }

    @RequestMapping(value = Uris.INVOICES + Uris.USER_MOBILE, method = RequestMethod.GET)
    public InvoicesWrapper getInvoicesByUserMobile(@PathVariable(value = "mobile") long userMobile) throws NotFoundUserMobileException {
        if (!this.userController.userExistsByMobile(userMobile))
            throw new NotFoundUserMobileException();

        return this.invoiceController.getInvoicesByUserMobile(userMobile);
    }
}
