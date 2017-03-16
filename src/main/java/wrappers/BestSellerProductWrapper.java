package wrappers;

public class BestSellerProductWrapper {

    private Long idProduct;

    private String description;

    private int totalAmount;

    public BestSellerProductWrapper() {
    }

    public BestSellerProductWrapper(Long idProduct, String description, int totalAmount) {
        this.idProduct = idProduct;
        this.description = description;
        this.totalAmount = totalAmount;
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
        return "BestSellerProductWrapper [idProduct=" + idProduct + ", description=" + description + ", totalAmount=" + totalAmount + "]";
    }

}
