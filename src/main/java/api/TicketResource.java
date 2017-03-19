package api;

import controllers.TicketController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrappers.TicketWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class TicketResource {

    private TicketController ticketController;

    @Autowired
    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public List<TicketWrapper> listTickets() {
        return ticketController.findAll();
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
