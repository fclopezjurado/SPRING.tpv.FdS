package wrappers;

import entities.core.Article;

import java.math.BigDecimal;

public class ArticleWrapper extends ProductWrapper {

    private int stock;

    private BigDecimal wholesalePrice;

//    private ProviderWrapper providerWrapper;
//    
    private int providerID;

    public ArticleWrapper() {

    }

    public ArticleWrapper(Article article) {

        super(article.getId(), article.getReference(), article.getDescription(), article.getRetailPrice());
        this.stock = article.getStock();
        this.wholesalePrice = article.getWholesalePrice();
        this.providerID = article.getProvider().getId();
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

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

//    public ProviderWrapper getProviderWrapper() {
//        return providerWrapper;
//    }

//    public void setProviderWrapper(ProviderWrapper providerWrapper) {
//        this.providerWrapper = providerWrapper;
//    }
    
    

}
