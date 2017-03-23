package entities.core;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ComponentProduct {
    
    @Id
    private long id;
    
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    public ComponentProduct() {
    }
    
    public ComponentProduct(long id) {
        this.id = id;
    }
    @Transient
    public abstract boolean isFamily();
    @Transient
    public abstract void add(ComponentProduct componentFamily);
    @Transient
    public abstract void remove(ComponentProduct componentFamily);
    @Transient
    public abstract List<Product> getAllProducts();
    
    @Override
    public int hashCode() {
        return (int) id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return id == ((ComponentProduct) obj).id;
    }


}
