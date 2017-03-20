package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.EmbroideryController;
import wrappers.EmbroideryWrapper;

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
    
    @RequestMapping(method = RequestMethod.DELETE, value = Uris.ID)
    public void removeEmbroidery(@PathVariable(value = "id")  long id) {
        this.embroideryController.removeEmbroidery(id);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public void addEmbroidery(@RequestBody  EmbroideryWrapper embroideryWrapper){
        this.embroideryController.addEmbroidery(embroideryWrapper);
}
}
