package daos.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entities.core.Family;
import entities.core.Product;

public class FamilyDaoImpl implements FamilyExtented{
    
    @Autowired
    private FamilyDao familyDao;

    @Override
    public List<Product> findProductByFamilyName(String name) {
        Family family = familyDao.findByName(name);
        return family.getAllProducts();
    }

    @Override
    public List<Family> findAllFamilies() {
        return familyDao.findAll();
    }
    
}
