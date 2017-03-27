package controllers;

import daos.core.TicketDao;
import entities.core.Shopping;
import entities.core.Ticket;
import entities.core.TicketState;
import entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import services.MailService;
import wrappers.TicketWrapper;
import wrappers.TicketsWrapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {

    private final int PADDING = 10000;

    private TicketDao ticketDao;

    private Ticket lastTicket;
    
    @Autowired
    private MailService mailService;

    @Autowired
    public void setTicketDao(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public TicketsWrapper findAll() {
        TicketsWrapper ticketsWrapper = new TicketsWrapper();
        ticketsWrapper.wrapTickets(ticketDao.findAll());
        return ticketsWrapper;
    }

    public TicketWrapper getTicketByReference(String reference) {
        Ticket ticket = ticketDao.findByReference(reference);

        TicketWrapper ticketWrapper = new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                ticket.getShoppingList(), ticket.getUser());
        return (ticketWrapper);
    }

    public TicketWrapper getTicketByReferenceNotCommitted(String reference) {
        Ticket ticket = ticketDao.findByReference(reference);
        if (ticket != null && ticket.getTicketState() != TicketState.COMMITTED) {
            return new TicketWrapper(ticket);
        }
        return null;
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

        sendTicketEmail(ticket);

        ticketWrapper = new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                ticket.getShoppingList(), ticket.getUser());

        return ticketWrapper;
    }

    public TicketWrapper updateTicket(TicketWrapper ticketWrapper) {
        Ticket ticket = ticketDao.findById(ticketWrapper.getId());
        if (ticket != null) {
            ticket.setShoppingList(ticketWrapper.getShoppingList());
            ticket.setTicketState(ticketWrapper.getTicketState());
            ticketDao.save(ticket);
            if (ticket.getTicketState() == TicketState.CLOSED) {
                sendTicketEmail(ticket);
            }
            return new TicketWrapper(ticket);
        }
        else {
            return null;
        }
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

    public TicketsWrapper getByUserMobile(long userMobile) {
        TicketsWrapper ticketsWrapper = new TicketsWrapper();
        ticketsWrapper.wrapTickets(this.ticketDao.findByUserMobile(userMobile));
        return ticketsWrapper;
    }

    public TicketsWrapper getByUserEmail(String userEmail) {
        TicketsWrapper ticketsWrapper = new TicketsWrapper();
        ticketsWrapper.wrapTickets(this.ticketDao.findByUserEmail(userEmail));
        return ticketsWrapper;
    }

    public TicketWrapper getByInvoiceID(int invoiceID) {
        Ticket ticket = this.ticketDao.findByInvoiceID(invoiceID);
        return new TicketWrapper(ticket.getId(), ticket.getCreated(), ticket.getReference(), ticket.getTicketState(),
                ticket.getShoppingList(), ticket.getUser());
    }

    private void sendTicketEmail(Ticket ticket) {
        User user = ticket.getUser();
        if (user != null) {
            mailService.sendHtmlMail("miw.upm.fds@gmail.com", "miw.upm.fds@gmail.com", "Cambio en el estado de su ticket ",
                    "<html><body><center>Test</center></body></html>");
        }
    }

}
