package wrappers;

public class FamilyWrapper {
    
    private long id;
    
    private String name;
    
    private String familyDescription;
    
    private MockProductsWrapper mockProductsWrapper;
    
    public FamilyWrapper() {
    }
    
    public FamilyWrapper(Long id, String name, String familyDescription, MockProductsWrapper mockProductsWrapper){
        this.id = id;
        this.name = name;
        this.familyDescription = familyDescription;
        this.mockProductsWrapper = mockProductsWrapper;
    }
    
    public long getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setFamilyDescription(String familyDescription) {
        this.familyDescription = familyDescription;
    }
    
    public String getFamilyDescription() {
        return familyDescription;
    }
    
    public void setProductWrappers(MockProductsWrapper mockProductsWrapper) {
        this.mockProductsWrapper = mockProductsWrapper;
    }
    
    public MockProductsWrapper getProductWrappers() {
        return mockProductsWrapper;
    }
    
    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return id == ((FamilyWrapper) obj).id;
    }
    
    
}
