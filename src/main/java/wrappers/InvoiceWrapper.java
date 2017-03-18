package wrappers;

public class InvoiceWrapper {

    private long ticket_id;

    public InvoiceWrapper(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    @Override
    public String toString() {
        return "InvoiceWrapper [ticket_id=" + ticket_id + "]";
    }

}
