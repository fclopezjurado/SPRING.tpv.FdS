package wrappers;
 
 public class ProviderWrapper {
 
     private int id;
 
     private String company;
 
     private String address;
 
     private long mobile;
 
     private String paymentConditions;
 
     private String note;
 
     public ProviderWrapper() {
 
     }
 
     public ProviderWrapper(int id, String company, String address, long mobile, String paymentConditions, String note) {
         this.id = id;
         this.company = company;
         this.address = address;
         this.mobile = mobile;
         this.paymentConditions = paymentConditions;
         this.note = note;
     }
 
     public int getId() {
         return id;
     }
 
     public void setId(int id) {
         this.id = id;
     }
 
     public String getCompany() {
         return company;
     }
 
     public void setCompany(String company) {
         this.company = company;
     }
 
     public String getAddress() {
         return address;
     }
 
     public void setAddress(String address) {
         this.address = address;
     }
 
     public long getMobile() {
         return mobile;
     }
 
     public void setMobile(long mobile) {
         this.mobile = mobile;
     }
 
     public String getPaymentConditions() {
         return paymentConditions;
     }
 
     public void setPaymentConditions(String paymentConditions) {
         this.paymentConditions = paymentConditions;
     }
 
     public String getNote() {
         return note;
     }
 
     public void setNote(String note) {
         this.note = note;
     }
 
     @Override
     public int hashCode() {
         return id;
     }
 
     @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         if (getClass() != obj.getClass())
             return false;
         return id == ((ProviderWrapper) obj).id;
     }
 
 }

