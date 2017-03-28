package api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controllers.AdminController;
import controllers.PopulationController;

@RestController
@RequestMapping(Uris.VERSION + Uris.POPULATE)
public class PopulationResource {
    private PopulationController populationController;

    @Autowired
    public void setPopulateController(PopulationController populationController) {
        this.populationController = populationController;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void populate() throws IOException {
    	populationController.populate();
    }
    
}


