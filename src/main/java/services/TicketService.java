package services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import daos.core.TicketDao;
import entities.core.Ticket;

public class TicketService {

    private int PADDING = 10000;

    @Autowired
    private TicketDao ticketDao;

    private Ticket lastTicket;

    @Autowired
    public long getNextTicketId() {
        lastTicket = ticketDao.findFirstByOrderByIdDesc();
        long firstTicketofDay = getFirstTicketOfDay();

        if (firstTicketofDay > lastTicket.getId()) {
            LogManager.getLogger(this.getClass()).info("devuelvo el primer ticket del dia: " + Long.valueOf(firstTicketofDay));
            return firstTicketofDay;
        } else {
            LogManager.getLogger(this.getClass()).info("devuelvo el siguiente ticket en secuencia: " + Long.valueOf(lastTicket.getId() + 1));
            return (lastTicket.getId() + 1);
        }
    }
    
    @Autowired
    public long getFirstTicketOfDay() {
        long todayFormated = getTodayUSformat();
        long firstTicketofDay = (todayFormated * PADDING) + 1;
        return firstTicketofDay;
    }

    @Autowired
    public long getTodayUSformat() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Long todayFormated = Long.parseLong(formatter.format(today));
        return todayFormated;
    }

    @Autowired
    public Ticket getLastTicket() {
        return lastTicket;
    }

}
