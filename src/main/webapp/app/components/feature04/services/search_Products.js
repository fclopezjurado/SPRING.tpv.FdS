angular.module("tpv").factory("busquedaDeProductos", function ($http) {
    const baseURL 			= "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0/products";
        
    
 	var productosAjax = {"code":200,"data":[

 		{
 			"id": 13,
 			"reference": "REFERENCE13",
 			"description": "DESCRIPTION 13",
 		},
 		{
 			"id": 14,
 			"reference": "REFERENCE14",
 			"description": "DESCRIPTION 14",
 		},
 		{
 			"id": 15,
 			"reference": "REFERENCE15",
 			"description": "DESCRIPTION 15",
 			
 		},
 		{
 			"id": 16,
 			"reference": "REFERENCE16",
 			"description": "DESCRIPTION 16",
 		}
 	]};
    
 	

    

    
    
    /**
	 * TODO: This end-point will return a set of ticket wrappers, when the user
	 * has information in "DNI", "USERNAME" and "ADDRESS" attributes. The user
	 * who uses this end point must be logged in.
	 */
    return {
    	getProducts: function (products) {
            
            return productosAjax;
        }
    }
	
	    

	  
}); 