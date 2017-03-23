package entities.core;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ComponentProduct {

    @Id
    private long id;

    public ComponentProduct() {
    }

    public ComponentProduct(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public abstract boolean isFamily();

    @Transient
    public abstract void add(ComponentProduct componentFamily);

    @Transient
    public abstract void remove(ComponentProduct componentFamily);

    @Transient
    public abstract int numberOfProducts();

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
