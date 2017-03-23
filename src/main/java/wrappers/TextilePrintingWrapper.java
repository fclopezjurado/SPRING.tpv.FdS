package wrappers;

import entities.core.TextilePrinting;

import java.math.BigDecimal;
 
 public class TextilePrintingWrapper extends ProductWrapper {
 
     private String type;
 
     public TextilePrintingWrapper() {
 
     }

     public TextilePrintingWrapper(TextilePrinting textilePrinting) {

         super(textilePrinting.getId(), textilePrinting.getReference(), textilePrinting.getDescription(), textilePrinting.getRetailPrice());
         this.type = textilePrinting.getType();
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
