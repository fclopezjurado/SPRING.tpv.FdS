package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.exceptions.MalformedFieldxception;
import controllers.TextilePrintingController;
import wrappers.ProductsOutFilterWrapper;
import wrappers.TextilePrintingWrapper;
import wrappers.TextilePritingFilterWrapper;

@RestController
@RequestMapping(Uris.VERSION + Uris.TEXTILE_PRINTING)
public class TextilePrintingResource {

    private TextilePrintingController textilePrintingController;

    @Autowired
    public void setTextilePrintingController(TextilePrintingController textilePrintingController) {
        this.textilePrintingController = textilePrintingController;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public List<TextilePrintingWrapper> getAll() {
        return textilePrintingController.getAll();
    }
     

    @RequestMapping(value = Uris.FILTER+Uris.MOCK,method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilterMock(@RequestBody TextilePritingFilterWrapper textile){
        List<ProductsOutFilterWrapper> productosSalidaMock= new ArrayList<ProductsOutFilterWrapper> ();
        ProductsOutFilterWrapper productoMock= new ProductsOutFilterWrapper();
        productoMock.setId(0);
        productoMock.setReference("referenceMock");
        productoMock.setDescription("descriptionMock");
        productosSalidaMock.add(productoMock);
        return productosSalidaMock;
    }

    @RequestMapping(value = Uris.FILTER,method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilter(@RequestBody TextilePritingFilterWrapper textile) throws MalformedFieldxception{
        this.validarCamposApi(textile);
        List<ProductsOutFilterWrapper> productosSalida = this.textilePrintingController.getTextilePrintingByFilter(textile);
        return productosSalida;
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeTextilePrinting(@PathVariable(value = "id")  long id) {
      System.out.println(id);
        this.textilePrintingController.removeTextilePrinting(id);
    }
    

    @RequestMapping(method = RequestMethod.POST)
    public void addTextilePrinting(@RequestBody TextilePrintingWrapper textilePrintingWrapper) {
        this.textilePrintingController.addTextilePrinting(textilePrintingWrapper);
    }
    
    @RequestMapping(value = Uris.ARTICLES + Uris.ID, method = RequestMethod.PUT)
    public void updateTextilePrinting(@RequestBody TextilePrintingWrapper textilePrintingWrapper) {
        this.textilePrintingController.updateTextilePrinting(textilePrintingWrapper);
    }
    
    private void validarCamposApi(TextilePritingFilterWrapper textile) throws MalformedFieldxception {
        if (textile == null)
            throw new MalformedFieldxception();
        if (textile.getDescription() == null)
            throw new MalformedFieldxception();
        if (textile.getMaxRetailPrice() == null)
            throw new MalformedFieldxception();
        if (textile.getMinRetailPrice() == null)
            throw new MalformedFieldxception();
        if (textile.getReference() == null)
            throw new MalformedFieldxception();
        if (textile.getType() == null)
            throw new MalformedFieldxception();

    }

}
