package api;

import controllers.EmbroideryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wrappers.EmbroideryFilterWrapper;
import wrappers.EmbroideryWrapper;
import wrappers.ProductsOutFilterWrapper;

import java.util.ArrayList;
import java.util.List;

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
}
