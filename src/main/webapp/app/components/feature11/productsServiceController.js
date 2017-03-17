tpv.controller('ProductsServiceController', [ '$timeout', 'f11Service',
		function($timeout, f11Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.ticket = null;
			vm.error = false;
			vm.response = "";

			//TODO Check function and develop implementation 
			function productsServiceState(reference) {
				const delay = 2000;

				f11Service.getAll().then(function(result) {
					vm.completed = true;
					vm.ticket = result.ticket;
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			productsServiceState(reference);
		} ]);