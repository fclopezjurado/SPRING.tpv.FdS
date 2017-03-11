tpv.controller('createController', [
		'$timeout',
		'f07Service',
		function($timeout, f07Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.value = 0;
			vm.crear = crear;
			vm.expiration = new Date();

			function crear() {
				const
				delay = 2000;
				f07Service.crear(vm.value, vm.expiration).then(
						function(result) {
							// promise was fullfilled
							vm.completed = true;
							vm.response = "Referencia vale creado: "+result.reference;
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