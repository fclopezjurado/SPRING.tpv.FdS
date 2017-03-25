tpv.service('f05Service', ['$http', '$q', function ($http, $q) {
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
   
   
   /**
    * family
    *  var productsAjax = {"code":200,"data":[

		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		},
		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		},
		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		},
		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		}

	]};
    *  */ 
   
   
   this.getAll = function() {
	   let config = {
 	     method: 'GET',
 	     url: urlBase + "/families",
	  };
	   return this.request(config); 
   }
   
   /**
    * family name
    * var nameAjax = {"code":200,"data":[

		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		},
		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		},
		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		},
		{
			name: 'Family Name',
			description: 'Family Description',
			num: 12  //Number of the products in this family
		}

	]}; 
    *  */  
    
   
   this.getFamilyName= function (name) {
		let config = {
				method: 'POST',
				url: urlBase + "/families/" + name
		};
		return this.request(config);  
   }
  
   
}]);