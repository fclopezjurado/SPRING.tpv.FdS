package api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wrappers.FamilyWrapper;
import wrappers.ProductsFilterByFamilyWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.FAMILIES)
public class FamilyResource {

    @RequestMapping(value = Uris.FAMILY_NAME, method = RequestMethod.POST)
    public List<ProductsFilterByFamilyWrapper> getProductByFamily(@PathVariable(value = "name") String name){
        List<ProductsFilterByFamilyWrapper> list = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            list.add(new ProductsFilterByFamilyWrapper(1L+1, "reference"+i, "description"+i, new BigDecimal(20+i)));
        }
        return list;
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<FamilyWrapper> getAllFamilies(){
        List<FamilyWrapper> list = new ArrayList<FamilyWrapper>();
        for (int i = 0; i < 4; i++){
            list.add(new FamilyWrapper(1L+i, "family"+i, "description"+i));
        }
        return list;
    }
}