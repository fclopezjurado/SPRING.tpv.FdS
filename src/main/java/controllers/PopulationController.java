package controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import services.PopulationService;

@Controller
public class PopulationController {
	PopulationService populationService;
	
	public void populate() throws IOException{
		populationService.readFromPath();
		
	}
	
	@Autowired
	public void setPopulationService(PopulationService populationService){
		this.populationService = populationService;
	}
	
}
