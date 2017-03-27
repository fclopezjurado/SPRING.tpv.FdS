tpv.service('busquedaDeProductos', function ($http, $q) {
    "use strict";

    const urlBase = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0";


    this.request = function (config) {
        let deferred = $q.defer();
        $http(config).then(function (response) {
            deferred.resolve(response.data);
        }, function (response) {
            let errorMsg;
            if (response.data.error === undefined) {
                errorMsg = "";
            } else {
                errorMsg = " --- " + response.data.error + ":" + response.data.description;
            }
            deferred.reject(
                "Error (" + response.status + ":" + response.statusText + ")" + errorMsg);
        });
        return deferred.promise;
    }
	
	
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
     * TODO:
	 */


    	this.getProducts= function (products) {
    		let config = {
    				method: 'POST',
    				url: urlBase + "/products",
    				data:products
    		};
    		return this.request(config); 

            
           // return productosAjax;
        }


}); 
