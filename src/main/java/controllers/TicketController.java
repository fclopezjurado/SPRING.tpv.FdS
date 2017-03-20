package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.TicketDao;
import entities.core.Shopping;
import entities.core.Ticket;
import entities.core.TicketState;
import entities.users.User;
import wrappers.TicketWrapper;

@Controller
public class TicketController {

    private final int PADDING = 10000;

    @Autowired
    private TicketDao ticketDao;

    private Ticket lastTicket;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public List<TicketWrapper> findAll() {
        List<Ticket> tickets = ticketDao.findAll();
        List<TicketWrapper> ticketsWrapper = new ArrayList<TicketWrapper>();

        for (Ticket ticket : tickets) {
            ticketsWrapper.add(new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                    ticket.getShoppingList(), ticket.getUser()));
        }

        return ticketsWrapper;
    }

    public TicketWrapper getTicketByReference(String reference) {
        Ticket ticket = ticketDao.findByReference(reference);

        TicketWrapper ticketWrapper = new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                ticket.getShoppingList(), ticket.getUser());
        // TODO Feature 11: If ticket state is Committed no Wrapper will be returned
        return (ticketWrapper);
    }

    public TicketWrapper createTicket(List<Shopping> shoppingList, User user) {
        Ticket ticket;
        TicketWrapper ticketWrapper;
        if (user == null) {
            ticket = new Ticket(getNextTicketId(), TicketState.COMMITTED);
        } else {
            ticket = new Ticket(getNextTicketId(), TicketState.OPENED);
            ticket.setUser(user);
        }
        for (Shopping shopping : shoppingList) {
            ticket.addShopping(shopping);
        }

        ticketDao.save(ticket);

        ticketWrapper = new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                ticket.getShoppingList(), ticket.getUser());

        // TODO Feature 11: Send an email after creation
        return ticketWrapper;
    }

    public TicketWrapper updateTicket(TicketWrapper ticketWrapper) {
        // TODO Implement this method
        // TODO Feature 11: Check status after each update
        return null;
    }

    public boolean ticketExistsByReference(String ticketReference) {
        Ticket ticket = ticketDao.findByReference(ticketReference);

        if (ticket != null) {
            return ticket.getReference().equals(ticketReference);
        }

        return false;
    }

    public long getNextTicketId() {
        lastTicket = ticketDao.findFirstByOrderByIdDesc();
        long firstTicketofDay = getFirstTicketOfDay();

        if (firstTicketofDay > lastTicket.getId()) {
            return firstTicketofDay;
        } else {
            return (lastTicket.getId() + 1);
        }
    }

    public long getFirstTicketOfDay() {
        long todayFormated = getTodayUSformat();
        long firstTicketofDay = (todayFormated * PADDING) + 1;
        return firstTicketofDay;
    }

    public long getTodayUSformat() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Long todayFormated = Long.parseLong(formatter.format(today));
        return todayFormated;
    }

    public Ticket getLastTicket() {
        return lastTicket;
    }

}
