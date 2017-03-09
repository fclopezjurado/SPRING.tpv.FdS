package wrappers;
 
 import java.math.BigDecimal;
 
 public class ArticleWrapper extends ProductWrapper {
 
     private int stock;
 
     private BigDecimal wholesalePrice;
 
     private ProviderWrapper provider;
 
     public ArticleWrapper() {
 
     }
 
     public ArticleWrapper(long id, String reference, String description, BigDecimal retailPrice, int stock, BigDecimal wholesalePrice,
             ProviderWrapper provider) {
         super(id, reference, description, retailPrice);
         this.stock = stock;
         this.wholesalePrice = wholesalePrice;
         this.provider = provider;
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
 
     public ProviderWrapper getProvider() {
         return provider;
     }
 
     public void setProvider(ProviderWrapper provider) {
         this.provider = provider;
     }
 
 }

