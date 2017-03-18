package controllers;

import org.springframework.stereotype.Controller;

import wrappers.InvoiceWrapper;

@Controller
public class InvoiceController {

    public void createInvoice(String ticketReference) {
        // TODO Implement this method
    }

    public InvoiceWrapper findByTicketReference(String ticketReference) {
        // TODO Implement this method
        return new InvoiceWrapper(1L);
    }
}
