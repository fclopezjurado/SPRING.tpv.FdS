package wrappers;

import java.math.BigDecimal;

public class ProductFilterWrapper {
    private String reference;

    private BigDecimal minRetailPrice;

    private BigDecimal maxRetailPrice;

    private String description;

    
    public ProductFilterWrapper(){
        
    }
    
    
    public ProductFilterWrapper(String reference, BigDecimal minRetailPrice, BigDecimal maxRetailPrice, String description) {
        super();
        this.reference = reference;
        this.minRetailPrice = (minRetailPrice==null) ? new BigDecimal("0"): minRetailPrice;
        this.maxRetailPrice = (maxRetailPrice==null) ? new BigDecimal("0"): minRetailPrice;;
        this.description = description;
    }



    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getMinRetailPrice() {
        return minRetailPrice;
    }

    public void setMinRetailPrice(BigDecimal minRetailPrice) {
        this.minRetailPrice = minRetailPrice;
    }

    public BigDecimal getMaxRetailPrice() {
        return maxRetailPrice;
    }

    public void setMaxRetailPrice(BigDecimal maxRetailPrice) {
        this.maxRetailPrice = maxRetailPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
