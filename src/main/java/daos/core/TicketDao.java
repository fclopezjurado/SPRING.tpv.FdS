package daos.core;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Ticket;

public interface TicketDao extends JpaRepository<Ticket, Long> {

    Ticket findByReference(String reference);

    @Query("select count(t) from Ticket t where t.created BETWEEN ?1 AND ?2")
    public int countTicketsBetweenDates(Calendar inicio, Calendar fin);

}
