package entities.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Invoice {

    @Id
    private int id;

    @OneToOne
    private Ticket ticket;

    public Invoice() {
    }

    public Invoice(int id, Ticket ticket) {
        this.id = id;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((Invoice) obj).id;
    }

    @Override
    public String toString() {
        return "Invoice[" + id + ": ticket=" + ticket + "]";
    }

}
