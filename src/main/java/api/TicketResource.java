package api;

import org.springframework.web.bind.annotation.*;
import wrappers.TicketWrapper;

import java.util.List;

@RestController
@RequestMapping(Uris.VERSION)
public class TicketResource {

    @RequestMapping(value = Uris.TICKETS, method = RequestMethod.GET)
    public List<TicketWrapper> listTickets(@RequestParam String reference) {
        // TODO Implement ticket list
        return null;
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
