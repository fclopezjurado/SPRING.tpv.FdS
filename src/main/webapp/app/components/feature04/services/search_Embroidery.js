  tpv.service('busquedaDeEmbroidery', function ($http, $q) {
		"use strict"; 
		
	const urlBase="http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0";
        
    
	this.request = function(config) {
	      let deferred = $q.defer();
	      $http(config).then(function (response) {
	    	  deferred.resolve(response.data);
	      }, function (response){
	    	  let errorMsg;
	    	  if(response.data.error === undefined) {
	    		  errorMsg="";
	    	  }else{
	    		  errorMsg = " --- " + response.data.error + ":" + response.data.description;
	    	  }
	    	  deferred.reject( 
	    		 "Error (" + response.status + ":" + response.statusText + ")" + errorMsg );
	      });
	      return deferred.promise;	   
	}  
    
 	
    
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
 	this.getProducts= function (products) {
		let config = {
				method: 'POST',
				url: urlBase + "/embroidery/byFilter",
				data:products
		};
		this.request(config); 
        
        return embroideries;
    }
	
	    

	  
}); 