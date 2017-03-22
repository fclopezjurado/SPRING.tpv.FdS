package controllers;

import daos.core.TicketDao;
import entities.core.Ticket;
import entities.core.TicketState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import wrappers.TicketWrapper;

@Controller
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    public TicketWrapper getTicketByReferenceNotCommitted(String reference) {
        Ticket ticket = ticketDao.findByReference(reference);
        if (ticket != null && ticket.getTicketState() != TicketState.COMMITTED) {
            return new TicketWrapper(ticket);
        }
        return null;
    }

    public TicketWrapper createTicket(TicketWrapper ticketWrapper) {
        // TODO Implement this method
        // TODO Feature 11: Send an email after creation
        return null;
    }

    public TicketWrapper updateTicket(TicketWrapper ticketWrapper) {
        // TODO Implement this method
        // TODO Feature 11: Check status after each update 
        return null;
    }

}
