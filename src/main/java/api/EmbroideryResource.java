package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.MalformedFieldxception;
import controllers.EmbroideryController;
import wrappers.EmbroideryFilterWrapper;
import wrappers.EmbroideryWrapper;
import wrappers.ProductsOutFilterWrapper;


@RestController
@RequestMapping(Uris.VERSION + Uris.EMBROIDERY)
public class EmbroideryResource {

    private EmbroideryController embroideryController;

    @Autowired
    public void setEmbroideryController(EmbroideryController embroideryController) {
        this.embroideryController = embroideryController;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EmbroideryWrapper> getAll() {
        return embroideryController.getAll();
    }

    @RequestMapping(value = Uris.FILTER, method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public List<ProductsOutFilterWrapper> getEmbroideryByFilter(@RequestBody EmbroideryFilterWrapper embroidery) throws MalformedFieldxception {
        this.validarCamposApi(embroidery);
        List<ProductsOutFilterWrapper> productosSalida = this.embroideryController.getEmroiderysByFilter(embroidery);
        return productosSalida;
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeEmbroidery(@PathVariable(value = "id") long id) {
        this.embroideryController.removeEmbroidery(id);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addEmbroidery(@RequestBody EmbroideryWrapper embroideryWrapper) {
        this.embroideryController.addEmbroidery(embroideryWrapper);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateEmbroidery(@RequestBody EmbroideryWrapper embroideryWrapper) {
        this.embroideryController.updateEmbroidery(embroideryWrapper);
    }
    

    private void validarCamposApi(EmbroideryFilterWrapper embroidery) throws MalformedFieldxception {
        if (embroidery == null)
            throw new MalformedFieldxception();
        if (embroidery.getDescription() == null)
            throw new MalformedFieldxception();
        if (embroidery.getMaxRetailPrice() == null)
            throw new MalformedFieldxception();
        if (embroidery.getMinRetailPrice() == null)
            throw new MalformedFieldxception();
        if (embroidery.getReference() == null)
            throw new MalformedFieldxception();
    }

    @RequestMapping(method = RequestMethod.GET,value = Uris.ID)
    public EmbroideryWrapper getEmbroidery(@PathVariable(value = "id") long id) {
        return this.embroideryController.getEmbroidery(id);

    }
}
