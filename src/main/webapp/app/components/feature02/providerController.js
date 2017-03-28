tpv.controller('ProviderController', [
		'$timeout',
		'f02Service',
		function($timeout, f02Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.providers;
			vm.company;
			vm.address; 
			vm.mobile;
			vm.paymentConditions;
			vm.note;
			vm.providerSelected;
			vm.registration = registration;
			vm.update = update;
			vm.init = init;
			vm.getAll = getAll;
			vm.removeProvider = removeProvider;
			vm.respuesta = "";
			

			
			
			function registration() {
				const
				delay = 2000;

				f02Service.registration(vm.company, vm.address, vm.mobile, vm.paymentConditions, vm.note).then(function(result) {
					// promise was fullfilled
	            	vm.error = false;
					vm.completed = true;
					vm.response = "";
					vm.company = "";
					vm.address = ""; 
					vm.mobile = "";
					vm.paymentConditions = "";
					vm.note = "";
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
			
			function update() {
				const
				delay = 2000;

				f02Service.update(vm.providerSelected.id, vm.providerSelected.company, vm.providerSelected.address, vm.providerSelected.mobile, vm.providerSelected.paymentConditions, vm.providerSelected.note).then(function(result) {
	            	vm.error = false;
					vm.completed = true;
					vm.response = "";
					vm.company = "";
					vm.address = ""; 
					vm.mobile = "";
					vm.paymentConditions = "";
					vm.note = "";
					vm.providerSelected = ""
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
	            	vm.error = false;
					vm.completed = true;
					vm.response = result;
					vm.providers = result;
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
			
			function init() {
				const
				delay = 10000;
				f02Service.getAll().then(function(result) {
	            	vm.error = false;
					vm.response = result;
					vm.providers = result;
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
			
			 function removeProvider(id) {
		            const
		                delay = 10000;
		            f02Service.removeProvider(id).then(function (result) {
		            	vm.error = false;
		                vm.completed = true;
		                vm.response = result;
		                vm.providers = getAll();
		                $timeout(function () {
		                    vm.completed = false;
		                }, delay)
		            }, function (errors) {
		                vm.error = true;
		                vm.response = errors;
		                $timeout(function () {
		                    vm.error = false;
		                }, delay)
		            });
		        }

		} ]);