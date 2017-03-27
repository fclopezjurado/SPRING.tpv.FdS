package wrappers;

import entities.core.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketsWrapper {

    private List<TicketWrapper> tickets;

    public TicketsWrapper() {
        this.tickets = new ArrayList<TicketWrapper>();
    }

    public List<TicketWrapper> getTickets() {
        return this.tickets;
    }

    public boolean isEmpty() {
        return this.tickets.isEmpty();
    }

    public TicketWrapper getFirstTicket() {
        if (this.tickets.isEmpty())
            return new TicketWrapper();

        return this.tickets.get(0);
    }

    public boolean userHasTicketsByMobile(long mobile) {
        if (!this.isEmpty())
            for (TicketWrapper ticket : this.tickets)
                if (ticket.getUser().getMobile() == mobile)
                    return true;

        return false;
    }

    public boolean userHasTicketsByEmail(String email) {
        if (!this.isEmpty())
            for (TicketWrapper ticket : this.tickets)
                if (ticket.getUser().getEmail().equals(email))
                    return true;

        return false;
    }

    public void wrapTickets(List<Ticket> tickets) {
        for (Ticket ticket : tickets)
            this.addTicketWrapper(new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                    ticket.getShoppingList(), ticket.getUser()));
    }

    public void addTicketWrapper(TicketWrapper ticket) {
        this.tickets.add(ticket);
    }

    @Override
    public String toString() {
        return "TicketsWrapper [tickets=" + tickets + "]";
    }

}
