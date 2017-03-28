package api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    

    @RequestMapping(value = Uris.FILTER,method = RequestMethod.POST)
    public List<ProductsOutFilterWrapper> getProductsByFilterMock(@RequestBody TextilePritingFilterWrapper textile){
        List<ProductsOutFilterWrapper> productosSalidaMock= new ArrayList<ProductsOutFilterWrapper> ();
        ProductsOutFilterWrapper productoMock= new ProductsOutFilterWrapper();
        productoMock.setId(0);
        productoMock.setReference("referenceMock");
        productoMock.setDescription("descriptionMock");
        productosSalidaMock.add(productoMock);
        return productosSalidaMock;
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
    
    @RequestMapping(method = RequestMethod.PUT)
    public void updateTextilePrinting(@RequestBody TextilePrintingWrapper textilePrintingWrapper) {
        this.textilePrintingController.updateTextilePrinting(textilePrintingWrapper);
    }
    
    @RequestMapping(method = RequestMethod.GET,value = Uris.ID)
    public TextilePrintingWrapper getTextilePrinting(@PathVariable(value = "id") long id) {
        return this.textilePrintingController.getTextilePrinting(id);
    }

}
