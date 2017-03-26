package wrappers;

import entities.core.Family;

public class FamilyWrapper {

    private long id;

    private String name;

    private String familyDescription;

    public FamilyWrapper() {
    }

    public FamilyWrapper(Family family) {
        this.id = family.getId();
        this.name = family.getName();
        this.familyDescription = family.getFamilyDescription();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyDescription() {
        return familyDescription;
    }

    public void setFamilyDescription(String familyDescription) {
        this.familyDescription = familyDescription;
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
