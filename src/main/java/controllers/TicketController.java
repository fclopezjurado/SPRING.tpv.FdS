package controllers;

import org.springframework.stereotype.Controller;
import wrappers.TicketWrapper;

@Controller
public class TicketController {

    public TicketWrapper getTicketByReference(String reference) {
        // TODO Implement this method
        // TODO Feature 11: If ticket state is Committed no Wrapper will be returned
        return null;
    }

    public TicketWrapper createTicket(TicketWrapper ticketWrapper) {
        // TODO Implement this method
        // TODO Feature 11: Send an email after creation
        return null;
    }

    public TicketWrapper updateTicket() {
        // TODO Implement this method
        // TODO Feature 11: Check status after each update 
        return null;
    }

}
