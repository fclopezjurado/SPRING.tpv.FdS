package wrappers;

public class InvoiceWrapper {

    private int id;

    private String ticketReference;

    public InvoiceWrapper() {
    }

    public InvoiceWrapper(int id, String ticketReference) {
        this.id = id;
        this.ticketReference = ticketReference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicketReference() {
        return this.ticketReference;
    }

    public void setTicketReference(String ticketReference) {
        this.ticketReference = ticketReference;
    }

    @Override
    public String toString() {
        return "InvoiceWrapper [id=" + this.id + ", ticketReference=" + this.ticketReference + "]";
    }

}
