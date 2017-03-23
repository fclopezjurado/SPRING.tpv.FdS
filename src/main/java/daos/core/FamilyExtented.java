package daos.core;

import java.util.List;

import entities.core.Family;
import entities.core.Product;

public interface FamilyExtented {
    
    public List<Product> findProductByFamilyName(String name);
    
    public List<Family> findAllFamilies();
}
