package api;

import controllers.TicketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrappers.TicketWrapper;

import java.util.List;

@RestController
@RequestMapping(Uris.VERSION)
public class TicketResource {

    private TicketController ticketController;

    @Autowired
    public void setAdminController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public List<TicketWrapper> listTickets() {
        // TODO Implement ticket list
        return null;
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
}
