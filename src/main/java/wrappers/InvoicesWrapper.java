package wrappers;

import java.util.ArrayList;
import java.util.List;

public class InvoicesWrapper {

    private List<InvoiceWrapper> invoices;

    public InvoicesWrapper() {
        this.invoices = new ArrayList<InvoiceWrapper>();
    }

    public List<InvoiceWrapper> getInvoices() {
        return invoices;
    }

    public boolean isEmpty() {
        return this.invoices.isEmpty();
    }

    public InvoiceWrapper getFirstInvoice() {
        if (this.invoices.isEmpty())
            return new InvoiceWrapper();

        return this.invoices.get(0);
    }

    public void addInvoiceWrapper(InvoiceWrapper invoice) {
        this.invoices.add(invoice);
    }

    @Override
    public String toString() {
        return "InvoicesWrapper [invoices=" + invoices + "]";
    }

}
