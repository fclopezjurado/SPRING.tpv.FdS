package wrappers;

import java.util.Calendar;

public class SaleOfProductWrapper {

    private Calendar saleDate;
    
    private Long idProduct;

    private String description;

    private int totalAmount;

    public SaleOfProductWrapper() {
    }

    public SaleOfProductWrapper(Calendar saleDate, Long idProduct, String description, int totalAmount) {
        this.saleDate = saleDate;
        this.idProduct = idProduct;
        this.description = description;
        this.totalAmount = totalAmount;
    }
    
    public Calendar getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Calendar saleDate) {
        this.saleDate = saleDate;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "SaleOfProductWrapper [saleDate=" + saleDate + ", idProduct=" + idProduct + ", description=" + description + ", totalAmount=" + totalAmount + "]";
    }

}
