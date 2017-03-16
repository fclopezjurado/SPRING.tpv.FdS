angular.module("tpv").factory("busquedaDeTextilePrinting", function ($http) {
    const baseURL 			= "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0/products";


 	
 	var textilePrintings = {"code":200,"data":[
 		{
 			"id": 9,
 			"reference": "REFERENCE9",
 			"description": "DESCRIPTION 9",
 			
 		},
 		{
 			"id": 10,
 			"reference": "REFERENCE10",
 			"description": "DESCRIPTION 10",
 			
 		},
 		{
 			"id": 11,
 			"reference": "REFERENCE11",
 			"description": "DESCRIPTION 11",
 			
 		},
 		{
 			"id": 12,
 			"reference": "REFERENCE12",
 			"description": "DESCRIPTION 12",
 			
 		}
 	]};
    

    
    
    
    /**
	 * TODO
	 */
    return {
    	getProducts: function (products) {
            
            return textilePrintings;
        }
    }
	
	    

	  
}); 