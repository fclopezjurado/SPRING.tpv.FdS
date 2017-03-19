package wrappers;

public class InvoiceWrapper {

    private int id;

    private long ticket_id;

    public InvoiceWrapper() {
    }

    public InvoiceWrapper(int id, long ticket_id) {
        this.id = id;
        this.ticket_id = ticket_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    @Override
    public String toString() {
        return "InvoiceWrapper [id=" + id + ", ticket_id=" + ticket_id + "]";
    }

}
