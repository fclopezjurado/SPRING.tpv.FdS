package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.TicketDao;
import entities.core.Ticket;
import wrappers.TicketWrapper;

@Controller
public class TicketController {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public List<TicketWrapper> findAll() {
        List<Ticket> tickets = ticketDao.findAll();
        List<TicketWrapper> ticketsWrapper = new ArrayList<TicketWrapper>();

        for (Ticket ticket : tickets)
            ticketsWrapper.add(new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                    ticket.getShoppingList(), ticket.getUser()));

        return ticketsWrapper;
    }

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

    public TicketWrapper updateTicket(TicketWrapper ticketWrapper) {
        // TODO Implement this method
        // TODO Feature 11: Check status after each update
        return null;
    }

    public boolean ticketExistsByReference(String ticketReference) {
        Ticket ticket = ticketDao.findByReference(ticketReference);
        
        if (ticket != null)
            return ticket.getReference().equals(ticketReference);
        
        return false;
    }

}
