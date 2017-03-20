tpv.service('f03Service', ['$http', '$q', function ($http, $q) {
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
 	     url: urlBase + "/articles",
	  };
	   
	  return this.request(config); 
   }
   
   this.getAllTextile = function() {
	   let config = {
 	     method: 'GET',
 	     url: urlBase + "/textilePrinting",
	  };
	   
  return this.request(config); 
   }
   
   this.getAllEmbroidery = function() {
	   let config = {
 	     method: 'GET',
 	     url: urlBase + "/embroidery",
	  };
	   
  return this.request(config); 
   }
   
	this.removeArticle = function(articleId) {
		let config = {
				method: 'DELETE',
				url: urlBase + "/articles/" + articleId
		};
		return this.request(config);
	}
	
	this.removeEmbroidery = function(embroideryId) {
		let config = {
				method: 'DELETE',
				url: urlBase + "/embroidery/" + embroideryId
		};
		return this.request(config);
	}
	
	this.removeTextilePrinting = function(textilePrintingId) {
		let config = {
				method: 'DELETE',
				url: urlBase + "/textilePrinting/" + textilePrintingId
		};
		return this.request(config);
	}
   
}]);