package api;

import api.exceptions.NotFoundTicketReferenceException;
import api.exceptions.NotFoundUserMobileException;
import controllers.InvoiceController;
import controllers.TicketController;
import controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrappers.InvoiceWrapper;
import wrappers.InvoicesWrapper;

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
    public InvoiceWrapper createInvoice(@RequestParam(value = "reference") String ticketReference) throws NotFoundTicketReferenceException {
        if (!this.ticketController.ticketExistsByReference(ticketReference))
            throw new NotFoundTicketReferenceException();

        return this.invoiceController.createInvoice(ticketReference);
    }

    @RequestMapping(value = Uris.INVOICES + Uris.USER_MOBILE, method = RequestMethod.GET)
    public InvoicesWrapper getInvoicesByUserMobile(@PathVariable(value = "mobile") long userMobile) throws NotFoundUserMobileException {
        if (!this.userController.userExistsByMobile(userMobile))
            throw new NotFoundUserMobileException();

        return this.invoiceController.getInvoicesByUserMobile(userMobile);
    }
}
