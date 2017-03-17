package entities.core;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Family implements ComponentProduct{
    
    @Id
    @GeneratedValue
    public int id;
    
    @Column(unique = true, nullable = false, length = 32)
    public String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    public List<ComponentProduct> products;
    
    public String desc;
    
    public Family(){      
    }
    
    public Family(String name, String desc, List<ComponentProduct> products) {
        this.name = name;
        this.desc = desc;
        this.products = products;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<ComponentProduct> getProducts() {
        return products;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setProducts(List<ComponentProduct> products) {
        this.products = products;
    }
    
    @Override
    public int hashCode() {
        return id;
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
        return id == ((Family) obj).id;
    }
    
    @Override
    public String toString() {
        return "\nFamily [id=" + id + ", name=" + name + ", description=" + desc + ",\n   productsList=" + products + "]";
    }

}
