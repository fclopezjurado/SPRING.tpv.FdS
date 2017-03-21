package wrappers;

public class FamilyWrapper {
    
    private long id;
    
    private String name;
    
    private String familyDescription;
    
    public FamilyWrapper() {
    }
    
    public FamilyWrapper(Long id, String name, String familyDescription){
        this.id = id;
        this.name = name;
        this.familyDescription = familyDescription;
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
