package wrappers;

public class BudgetProductWrapper {

    private String reference;
    
    private long productId;
    
    private int amount;
    
    public BudgetProductWrapper() {
        
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BudgetProductWrapper [reference=" + reference + ", productId=" + productId + ", amount=" + amount + "]";
    }
    
    
}
