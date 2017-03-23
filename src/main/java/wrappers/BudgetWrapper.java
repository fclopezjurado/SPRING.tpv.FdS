package wrappers;

import entities.core.Budget;
import entities.core.Shopping;

public class BudgetWrapper {

    private String reference;
    
    private String created;
    
    private float total;
    
    public BudgetWrapper(Budget budget) {
        reference = budget.getReference();
        created = budget.getCreated().toString();
        total = 0;
        for (Shopping shopping : budget.getShoppingList()){
            total += shopping.getRetailPrice().floatValue() * shopping.getAmount();
        }
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "BudgetWrapper [reference=" + reference + ", created=" + created + ", total=" + total + "]";
    }

}
