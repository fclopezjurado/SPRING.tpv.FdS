package wrappers;

import entities.core.Article;

import java.math.BigDecimal;

public class ArticleWrapper extends ProductWrapper {

    private int stock;

    private BigDecimal wholesalePrice;

    private ProviderWrapper providerWrapper;

    public ArticleWrapper() {

    }

    public ArticleWrapper(Article article) {

        super(article.getId(), article.getReference(), article.getDescription(), article.getRetailPrice());
        this.stock = article.getStock();
        this.wholesalePrice = article.getWholesalePrice();
        this.providerWrapper = new ProviderWrapper(article.getProvider());
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public ProviderWrapper getProviderWrapper() {
        return providerWrapper;
    }

    public void setProviderWrapper(ProviderWrapper providerWrapper) {
        this.providerWrapper = providerWrapper;
    }

}
