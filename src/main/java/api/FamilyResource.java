package api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import wrappers.FamilyWrapper;
import wrappers.MockProductsWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.FAMILIES)
public class FamilyResource {
    
    @RequestMapping(value = Uris.FAMILY_NAME, method = RequestMethod.POST)
    public FamilyWrapper getFamilyByNameMock(@PathVariable(value = "name") String name){
        MockProductsWrapper productWrapper = new MockProductsWrapper();
        return new FamilyWrapper(1L, name, "Description", productWrapper);
    }
}
