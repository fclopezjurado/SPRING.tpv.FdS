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
   
   this.findProviders = function() {
	   let config = {
 	     method: 'GET',
 	     url: urlBase + "/providers",
	  };
	   
	  return this.request(config); 
   }

    this.getAllTextile = function () {
        let config = {
            method: 'GET',
            url: urlBase + "/textilePrinting",
        };

        return this.request(config);
    }

    this.getAllEmbroidery = function () {
        let config = {
            method: 'GET',
            url: urlBase + "/embroidery",
        };

        return this.request(config);
    }

    // Falta corregir
    this.removeArticle = function (articleId) {
        let config = {
            method: 'DELETE',
            url: urlBase + "/articles/" + articleId
        };
        return this.request(config);
    }

    this.removeEmbroidery = function (embroideryId) {
        let config = {
            method: 'DELETE',
            url: urlBase + "/embroidery/" + embroideryId
        };
        return this.request(config);
    }

    this.removeTextilePrinting = function (textilePrintingId) {
        let config = {
            method: 'DELETE',
            url: urlBase + "/textilePrinting/" + textilePrintingId
        };
        return this.request(config);
    }

    // * Create
    this.addArticle = function (article) {
        let config = {
            method: 'POST',
            url: urlBase + "/articles",
            data: {
                'id': article.id,
                'reference': article.reference,
                'description': article.description,
                'retailPrice': article.retailPrice,
                'wholesalePrice': article.wholesalePrice,
                'providerID': article.providerID
            }
        };
        return this.request(config);
    }

    this.addEmbroidery = function (embroidery) {
        let config = {
            method: 'POST',
            url: urlBase + "/embroidery",
            data: {
                'id': embroidery.id,
                'reference': embroidery.reference,
                'description': embroidery.description,
                'retailPrice': embroidery.retailPrice,
                'stitches': embroidery.stitches,
                'colors': embroidery.colors,
                'squareMillimeters': embroidery.millimiters
            }
        };
        return this.request(config);
    }

    this.addTextilePrinting = function (textilePrinting) {
        let config = {
            method: 'POST',
            url: urlBase + "/textilePrinting",
            data: {
                'id': textilePrinting.id,
                'reference': textilePrinting.reference,
                'description': textilePrinting.description,
                'retailPrice': textilePrinting.retailPrice,
                'type': textilePrinting.type
            }
        };
        return this.request(config);
    }
    
    // * Update
    this.updateArticle = function (article) {
        let config = {
            metod: 'PUT',
            url: urlBase + "/articles",
            data:{
     	      'id': article.id,
  	    	  'reference': article.reference,
  	    	  'description': article.description,
  	    	  'retailPrice': article.retailPrice,
  	    	  'wholesalePrice': article.wholesalePrice,
  	    	  'stock': article.stock,
  	    	  'providerId': article.providerId
            }
        };
        return this.request(config);
    }

    this.updateEmbroidery = function (embroideryId) {
        let config = {
            method: 'PUT',
            url: urlBase + "/embroidery/" + embroideryId
        };
        return this.request(config);
    }

    this.updateTextilePrinting = function (textilePrintingId) {
        let config = {
            method: 'PUT',
            url: urlBase + "/textilePrinting/" + textilePrintingId
        };
        return this.request(config);
    }
    
    this.findArticle = function (articleId) {
        let config = {
            method: 'GET',
            url: urlBase + "/articles/" + articleId,
        };

        return this.request(config);
    }
    
    
   
}]);
