package wrappers;

import java.math.BigDecimal;

public class ArticleFilterWrapper extends ProductFilterWrapper {

    private int stock;

    private BigDecimal minWholesalePrice;

    private BigDecimal maxWholesalePrice;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getMinWholesalePrice() {
        return minWholesalePrice;
    }

    public void setMinWholesalePrice(BigDecimal minWholesalePrice) {
        this.minWholesalePrice = minWholesalePrice;
    }

    public BigDecimal getMaxWholesalePrice() {
        return maxWholesalePrice;
    }

    public void setMaxWholesalePrice(BigDecimal maxWholesalePrice) {
        this.maxWholesalePrice = maxWholesalePrice;
    }

}
