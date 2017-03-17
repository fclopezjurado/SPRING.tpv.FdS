package api;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import controllers.TicketController;
import wrappers.TicketWrapper;

@RestController
@RequestMapping(Uris.VERSION)
public class TicketResource {

    private TicketController ticketController;

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public List<TicketWrapper> listTickets(@RequestParam String reference) {
        // TODO Implement ticket list
        return null;
    }

    @RequestMapping(value = Uris.TICKETS + Uris.REFERENCE, method = RequestMethod.GET)
    public TicketWrapper getTicket(@PathVariable String reference ) {
        return (ticketController.getTicketByReference(reference));
    }

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.POST,params="")
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
