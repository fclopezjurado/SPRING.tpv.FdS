tpv.controller('UpdateArticleController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;
			
			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.updateArticle=updateArticle;
			vm.providers = [];
			vm.getArticle = getArticle;

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
					// handle errors
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
	        	    	        vm.article.push({"id": item['id'], "description": item['description']});
	        	    	    });
	        	    },
	        	    function error(errors){
	        	      console.log(errors);
	        	});
	        }
		
		} ]);