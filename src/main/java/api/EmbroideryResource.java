package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.EmbroideryController;
import wrappers.EmbroideryFilterWrapper;
import wrappers.EmbroideryWrapper;
import wrappers.ProductsOutFilterWrapper;
import wrappers.TicketWrapper;

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
    public List<ProductsOutFilterWrapper> getProductsByFilterMock(@RequestBody EmbroideryFilterWrapper embroidery) {
        List<ProductsOutFilterWrapper> productosSalidaMock = new ArrayList<ProductsOutFilterWrapper>();
        ProductsOutFilterWrapper productoMock = new ProductsOutFilterWrapper();
        productoMock.setId(0);
        productoMock.setReference("referenceMock");
        productoMock.setDescription("descriptionMock");
        productosSalidaMock.add(productoMock);
        return productosSalidaMock;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeEmbroidery(@PathVariable(value = "id") long id) {
        this.embroideryController.removeEmbroidery(id);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void addEmbroidery(@RequestBody EmbroideryWrapper embroideryWrapper) {
        this.embroideryController.addEmbroidery(embroideryWrapper);
    }
    
    @RequestMapping(value = Uris.ARTICLES + Uris.ID, method = RequestMethod.PUT)
    public void updateEmproidery(@RequestBody EmbroideryWrapper embroideryWrapper) {
        this.embroideryController.updateEmbroidery(embroideryWrapper);
    }
}
