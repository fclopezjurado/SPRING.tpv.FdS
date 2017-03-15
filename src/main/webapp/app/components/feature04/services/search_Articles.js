angular.module("tpv").factory("busquedaDeArticulos", function ($http) {
    const baseURL 			= "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0/products";
        
 	var articles = {"code":200,"data":[

 		{
 			"id": 1,
 			"reference": "REFERENCE1",
 			"description": "DESCRIPTION 1",
 		},
 		{
 			"id": 2,
 			"reference": "REFERENCE2",
 			"description": "DESCRIPTION 2",
 		},
 		{
 			"id": 3,
 			"reference": "REFERENCE3",
 			"description": "DESCRIPTION 3",
 			
 		},
 		{
 			"id": 4,
 			"reference": "REFERENCE4",
 			"description": "DESCRIPTION 4",
 		}
 	]};
        
    
    /**
	 * TODO 
	 */
    return {
    	getProducts: function (products) {
            
            return articles;
        }
    }
	
	    

	  
}); 