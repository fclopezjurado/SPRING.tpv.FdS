package wrappers;
 
 import java.math.BigDecimal;
 
 public class TextilePrintingWrapper extends ProductWrapper {
 
     private String type;
 
     public TextilePrintingWrapper() {
 
     }
 
     public TextilePrintingWrapper(long id, String reference, String description, BigDecimal retailPrice, String type) {
         super(id, reference, description, retailPrice);
         this.type = type;
     }
 
     public String getType() {
         return type;
     }
 
     public void setType(String type) {
         this.type = type;
     }
 
 }
