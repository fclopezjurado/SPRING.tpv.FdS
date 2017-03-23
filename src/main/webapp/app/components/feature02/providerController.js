tpv.controller('ProviderController', [
		'$timeout',
		'f02Service',
		function($timeout, f02Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.company;
			vm.address; 
			vm.mobile;
			vm.payment;
			vm.notes;
			vm.registration = registration;
			vm.getAll = getAll;
			vm.respuesta = "";
			


			function registration() {
				const
				delay = 2000;

				f02Service.registration(vm.company, vm.address, vm.mobile, vm.payment, vm.notes).then(function(result) {
					// promise was fullfilled
					vm.completed = true;
					vm.response = "";
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
				const
				delay = 10000;
				f02Service.getAll().then(function(result) {
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

		} ]);