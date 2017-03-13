tpv.service('f10Service', ['$http', '$q', function ($http, $q) {
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
   
   this.getAll = function() {
	   let config = {
 	     method: 'GET',
 	     url: urlBase + "/alarms",
	  };
	   
	  return this.request(config); 
   }
   
   this.getAllProducts = function() {
	   let config = {
		method: 'GET',
		url: urlBase + "/mock_products"
	   }
	   
	   return this.request(config);
   };

   this.createAlarm = function(name, products, type, number) {
	   let config = {
		method: 'POST',
		url: urlBase + "/alarms",
		data:{'name': name, 'products': products, 'type': type, 'numProducts': number}
	   };
	   return this.request(config);
   };
   
   this.editAlarm = function(id, name, type, number) {
	   let config = {
		method: 'PUT',
		url: urlBase + "/alarms",
		data:{'id': id, 'name': name, 'type': type, 'numProducts': number}
	   };
	   return this.request(config);
   };
   
}]);