tpv.controller('UpdateTextilePrintingController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.textileprinting=updateTextilePrinting;
		    vm.getAll = getAll;
		    vm.textiles = [];

			function updateTextilePrinting() {
				const
				delay = 10000;
				f03Service.updateTextilePrinting(vm.textilePrinting).then(function(result) {
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
	        function getAll() {
	            //const delay = 10000
	        	f03Service.getAllTextile().then(function success(response){
		        	      vm.data = response;
		        	      console.log(vm.data);
		        	    	    $.each(vm.data, function (i, item) {
		        	    	        vm.textiles.push({"id": item['id'], "description": item['description']});
		        	    	    });
		        	    },
		        	    function error(errors){
		        	      console.log(errors);
	            });

	        }
		} ]);