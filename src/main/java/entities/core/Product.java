package entities.core;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Product extends ComponentProduct {

    private String reference;

    private BigDecimal retailPrice;

    private String description;

    public Product() {
    }

    public Product(long id, String reference, BigDecimal retailPrice, String description) {
        super(id);
        this.reference = reference;
        this.retailPrice = retailPrice;
        this.description = description;
    }

    public long getId() {
        return super.getId();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void add(ComponentProduct componentFamily) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(ComponentProduct componentFamily) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isFamily() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int hashCode() {
        return (int) this.getId();
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
        return this.getId() == ((Product) obj).getId();
    }

    @Override
    public String toString() {
        return getId() + ": reference=" + reference + ", retailPrice=" + retailPrice + ", description=" + description;
    }

}
