package wrappers;

import java.util.ArrayList;
import java.util.List;

public class InvoicesWrapper {

    private List<InvoiceWrapper> invoices;

    public InvoicesWrapper() {
        this.invoices = new ArrayList<InvoiceWrapper>();
    }

    public InvoicesWrapper(List<InvoiceWrapper> invoices) {
        this.invoices = invoices;
    }

    public List<InvoiceWrapper> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceWrapper> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "InvoicesWrapper [invoices=" + invoices + "]";
    }

}
