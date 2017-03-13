angular.module("tpv").factory("busquedaDeEmbroidery", function ($http) {
    const baseURL 			= "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0/products";

    
    
    
 	
    
 	var embroideries = {"code":200,"data":[
 		{
 			"id": 5,
 			"reference": "REFERENCE5",
 			"description": "DESCRIPTION 5",
 		},
 		{
 			"id": 6,
 			"reference": "REFERENCE6",
 			"description": "DESCRIPTION 6",
 			
 		},
 		{
 			"id": 7,
 			"reference": "REFERENCE7",
 			"description": "DESCRIPTION 7",
 		},
 		{
 			"id": 8,
 			"reference": "REFERENCE8",
 			"description": "DESCRIPTION 8",
 		}
 	]};
 	
 	
    
    /**
	 * TODO:
	 */
    return {
    	getProducts: function (products) {
            
            return embroideries;
        }
    }
	
	    

	  
}); 