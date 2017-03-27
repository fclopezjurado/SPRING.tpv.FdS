package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import daos.core.FamilyDaoImpl;
import entities.core.Family;
import entities.core.Product;
import wrappers.FamilyWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

@Controller
public class FamilyController {
    
    private FamilyDaoImpl familyDaoImpl;
    
    @Autowired
    public void setFamilyDaoImpl(FamilyDaoImpl familyDaoImpl) {
        this.familyDaoImpl = familyDaoImpl;
    }
    
    public List<FamilyWrapper> getAllFamilies(){
        List<FamilyWrapper> familyWrappers = new ArrayList<>();
        for (Family family : familyDaoImpl.findAllFamilies()){
            familyWrappers.add(new FamilyWrapper(family));
        }
        return familyWrappers;
    }
    
    public List<ProductsFilterByFamilyWrapper> getProductsByFamilyName(String name){
        List<ProductsFilterByFamilyWrapper> productsFilterByFamilyWrappers = new ArrayList<>();
        if (familyDaoImpl.findProductByFamilyName(name) != null) {
            for (Product product : familyDaoImpl.findProductByFamilyName(name)){
                productsFilterByFamilyWrappers.add(new ProductsFilterByFamilyWrapper(product));
            }
        }
        return productsFilterByFamilyWrappers;
    }

}
