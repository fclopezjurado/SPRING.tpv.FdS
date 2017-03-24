package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.FamilyController;
import entities.core.Family;
import wrappers.FamilyWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Uris.VERSION + Uris.FAMILIES)
public class FamilyResource {
    
    private FamilyController familyController;
    
    @Autowired
    public void setFamilyController(FamilyController familyController) {
        this.familyController = familyController;
    }

    @RequestMapping(value = Uris.FAMILY_NAME, method = RequestMethod.POST)
    public List<ProductsFilterByFamilyWrapper> getProductByFamily(@PathVariable(value = "name") String name) {
        return familyController.getProductsByFamilyName(name);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<FamilyWrapper> getAllFamilies() {
        return familyController.getAllFamilies();
    }
}
