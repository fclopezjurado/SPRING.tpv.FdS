package entities.core;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Family extends ComponentProduct{
    
    @Column(unique=true)
    private String name;
    
    private String familyDescription;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade= CascadeType.PERSIST)
    private List<ComponentProduct> componentProducts;
    
    public Family() {
    }

    public Family(long id, String name, String familyDescription, List<ComponentProduct> componentProducts) {
        super(id);
        this.name = name;
        this.familyDescription = familyDescription;
        this.componentProducts = componentProducts;
    }
    
    public String getFamilyDescription() {
        return familyDescription;
    }
    
    public void setFamilyDescription(String familyDescription) {
        this.familyDescription = familyDescription;
    }
    
    @Override
    public void add(ComponentProduct componentProduct){
        if (componentProduct != null && this.isFamily()) {
            this.componentProducts.add(componentProduct);
        }
    }
    
    @Override
    public void remove(ComponentProduct componentProduct){
        if (componentProduct != null && this.isFamily()) {
            this.componentProducts.remove(componentProduct);
        }
    }
    
    @Override
    public boolean isFamily() {
        return true;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setProducts(List<ComponentProduct> componentFamilies) {
        this.componentProducts = componentFamilies;
    }
    
    
    public String getName() {
        return name;
    }
    
    public List<ComponentProduct> getProducts() {
        return componentProducts;
    }
     
    @Override
    public String toString() {
        return "\nFamily [" + super.toString() + "name=" + name + ", description=" + ",\n   productsList=" +"]";
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        if (this.componentProducts != null) {
            for (ComponentProduct componentProduct : this.componentProducts){
                if (!componentProduct.isFamily()){
                    products.add((Product)componentProduct);
                }else{
                    for(ComponentProduct componentProductChild : componentProduct.getAllProducts()){
                        products.add((Product)componentProductChild);
                    }
                }
            }
        }
        return products;
    }

}
