package controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.DataService;
import services.PopulationService;

@Controller
public class PopulationController {
	PopulationService populationService;
	DataService dataService;
	
	public void populate() throws IOException{
		dataService.deleteAllExceptAdmin();
		populationService.readFromPath();
	}
	
	@Autowired
	public void setPopulationService(PopulationService populationService){
		this.populationService = populationService;
	}
	
	@Autowired
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	
}
