package wrappers;

import java.util.Calendar;
import java.util.List;

import entities.core.Shopping;
import entities.core.TicketState;
import entities.users.User;

public class TicketWrapper {

    private long id;

    private Calendar created;

    private String reference;

    private TicketState ticketState;

    private List<Shopping> shoppingList;

    private User user;

    private TicketWrapper(long id, Calendar created, String reference, TicketState ticketState, List<Shopping> shoppingList, User user) {
        this.id = id;
        this.created = created;
        this.reference = reference;
        this.ticketState = ticketState;
        this.shoppingList = shoppingList;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }

    public List<Shopping> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Shopping> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TicketWrapper [id=" + id + ", created=" + created + ", reference=" + reference + ", ticketState=" + ticketState
                + ", shoppingList=" + shoppingList + ", user=" + user + "]";
    }

}
