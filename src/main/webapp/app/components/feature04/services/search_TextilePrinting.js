tpv.service('busquedaDeTextilePrinting', function ($http, $q) {
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

    this.getProducts = function (products) {
        let config = {
            method: 'POST',
            url: urlBase + "/textilePrinting/byFilter",
            data: products
        };
        return this.request(config);

       // return textilePrintings;
    }
	    

	  
}); 
