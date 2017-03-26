tpv.controller('UpdateArticleController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;
			
			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.updateArticle=updateArticle;
			vm.providers = [];
			vm.articles = [];
			vm.getArticle = getArticle;
			vm.findArticle = findArticle;
			vm.Mdescription;
			vm.Mstock;
			vm.Mreference;
			vm.Mprice;
			vm.Mwholesale;
			vm.Mprovider;

			function updateArticle() {
				const
				delay = 10000;
				f03Service.updateArticle(vm.article).then(function(result) {
					vm.completed = true;
					vm.response = result;
					
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			
			
	        
	        function getArticle() {
	        	f03Service.getAll().then(function success(response){
	        	      vm.data = response;
	        	      console.log(vm.data);
	        	    	    $.each(vm.data, function (i, item) {
	        	    	        vm.articles.push({"id": item['id'], "description": item['description']});
	        	    	    });
	        	    },
	        	    function error(errors){
	        	      console.log(errors);
	        	});
	        }
	        
	        
	        function findArticle(id) {
				const
				delay = 10000;
				f03Service.findArticle(id).then(function(result) {
					vm.completed = true;
					vm.response = result;
					vm.Mdescription=result.description;;
					vm.Mstock=result.stock;
					vm.Mreference=result.reference;
					vm.Mprice=result.retailPrice;
					vm.Mwholesale=result.wholesalePrice;
					vm.Mprovider=result.providerID;
					
					
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}

		
		} ]);