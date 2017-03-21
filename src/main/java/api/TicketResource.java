package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.NotFoundUserEmailException;
import api.exceptions.NotFoundUserMobileException;
import controllers.TicketController;
import controllers.UserController;
import wrappers.TicketWrapper;
import wrappers.TicketsWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class TicketResource {

    private TicketController ticketController;

    private UserController userController;

    @Autowired
    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public TicketsWrapper listTickets() {
        return ticketController.findAll();
    }

    @RequestMapping(value = Uris.TICKETS + Uris.REFERENCE, method = RequestMethod.GET)
    public TicketWrapper getTicket(@PathVariable String reference) {
        return (ticketController.getTicketByReference(reference));
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET, params = "reference")
    public TicketWrapper getTicketByReference(@RequestParam String reference) {
        return ticketController.getTicketByReference(reference);
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.POST)
    public TicketWrapper createTickets(@RequestBody TicketWrapper ticketWrapper) {
        // TODO Implement ticket creation
        return null;
    }

    @RequestMapping(value = Uris.TICKETS + Uris.ID, method = RequestMethod.PUT)
    public TicketWrapper updateTickets(@RequestBody TicketWrapper ticketWrapper) {
        // TODO Implement ticket modification
        return null;
    }

    @RequestMapping(value = Uris.TICKETS + Uris.USER_MOBILE_PATH + Uris.USER_MOBILE, method = RequestMethod.GET)
    public TicketsWrapper getTicketsByUserMobile(@PathVariable(value = "mobile") long userMobile) throws NotFoundUserMobileException {
        if (!this.userController.userExistsByMobile(userMobile))
            throw new NotFoundUserMobileException();

        return this.ticketController.getByUserMobile(userMobile);
    }

    @RequestMapping(value = Uris.TICKETS + Uris.USER_EMAIL_PATH + Uris.USER_EMAIL_PATH_VARIABLE, method = RequestMethod.GET)
    public TicketsWrapper getTicketsByUserEmail(@PathVariable(value = "email") String userEmail) throws NotFoundUserEmailException {
        if (!this.userController.userExistsByEmail(userEmail))
            throw new NotFoundUserEmailException();

        return this.ticketController.getByUserEmail(userEmail);
    }
}
