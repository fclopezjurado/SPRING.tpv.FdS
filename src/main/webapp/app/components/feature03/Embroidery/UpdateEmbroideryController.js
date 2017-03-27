tpv.controller('UpdateEmbroideryController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.embroidery = updateEmbroidery;
			vm.embroideries = [];
			vm.getEmbroidery = getEmbroidery;
			vm.findEmbroidery = findEmbroidery;
			vm.Mdescription;
			vm.Mreference;
			vm.Mprice;
			vm.Mstitches;
			vm.Mcolor;
			vm.Msquaremilimeters;
			vm.embroidery=updateEmbroidery;
		    vm.getAll = getAll;
		    vm.embroideries = [];


			function updateEmbroidery() {
				const
				delay = 10000;
				f03Service.updateEmbroidery(vm.embroidery).then(function(result) {
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

			
			   function getEmbroidery() {
		        	f03Service.getAllEmbroidery().then(function success(response){
		        	      vm.data = response;
		        	      console.log(vm.data);
		        	    	    $.each(vm.data, function (i, item) {
		        	    	        vm.embroideries.push({"id": item['id'], "description": item['description']});
		        	    	    });
		        	    },
		        	    function error(errors){
		        	      console.log(errors);
		        	});
		        }
		       function findEmbroidery(id) {
					const
					delay = 10000;
					f03Service.findEmbroidery(id).then(function(result) {
						vm.completed = true;
						vm.response = result;
						vm.Mdescription=result.description;
						vm.Mreference=result.reference;
						vm.Mprice=result.retailPrice;
						vm.Mstitches=result.stitches;
						vm.Mcolor=result.color;
						vm.Msquaremillimeters=result.squaremillimeters;
						
						
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

		
			
	        function getAll() {
	            //const delay = 10000
	        	f03Service.getAllEmbroidery().then(function success(response){
		        	      vm.data = response;
		        	      console.log(vm.data);
		        	    	    $.each(vm.data, function (i, item) {
		        	    	        vm.embroiderys.push({"id": item['id'], "description": item['description']});
		        	    	    });
		        	    },
		        	    function error(errors){
		        	      console.log(errors);
	            });

	        }
		} ]);