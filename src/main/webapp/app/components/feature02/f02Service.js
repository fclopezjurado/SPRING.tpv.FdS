tpv.service('f02Service', ['$http', '$q', function ($http, $q) {
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

   
   this.registration = function (company, address, mobile, paymentConditions, note){
	      let resource="providers";
		  let config = {
	 	      method: 'POST',
	 	      url: urlBase + "/"+ resource,
	 	      data: {'company':company, 'address': address, 'mobile': mobile, 'paymentConditions': paymentConditions, 'note': note}
		  };
	      return this.request(config);
	   }
   
   this.update = function (id, company, address, mobile, paymentConditions, note){
	      let resource="providers";
		  let config = {
	 	      method: 'PUT',
	 	      url: urlBase + "/"+ resource,
	 	      data: {'id':id, 'company':company, 'address': address, 'mobile': mobile, 'paymentConditions': paymentConditions, 'note': note}
		  };
	      return this.request(config);
	   }
   
   this.getAll = function() {
	   let config = {
 	     method: 'GET',
 	     url: urlBase + "/providers",
	  };
	   
	  return this.request(config); 
   }
   
   this.removeProvider = function (providerId) {
       let config = {
           method: 'DELETE',
           url: urlBase + "/providers/" + providerId
       };
       return this.request(config);
   }

}]);