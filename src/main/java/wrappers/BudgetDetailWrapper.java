package wrappers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.core.Budget;
import entities.core.Shopping;

public class BudgetDetailWrapper {

    private String reference;
    
    private Calendar created;
    
    private float total;
    
    private List<Shopping> shoppingList;
    
    public BudgetDetailWrapper() {
        
    }

    public BudgetDetailWrapper(Budget budget) {
        reference = budget.getReference();
        created = budget.getCreated();
        total = 0;
        shoppingList = new ArrayList<Shopping>();
        for (Shopping shopping : budget.getShoppingList()){
            total += shopping.getRetailPrice().floatValue() * shopping.getAmount();
            shoppingList.add(shopping);
        }
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public List<Shopping> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<Shopping> shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public String toString() {
        return "BudgetDetailWrapper [reference=" + reference + ", created=" + created + ", total=" + total + ", shoppingList="
                + shoppingList + "]";
    }

}
