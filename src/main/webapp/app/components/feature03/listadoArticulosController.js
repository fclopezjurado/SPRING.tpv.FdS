tpv.controller('listadoArticulosController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.articles = null;
			vm.error = false;

			function getAll() {
				const
				delay = 2000;

				f03Service.getAll().then(function(result) {
					vm.completed = true;
					vm.articles = result.articles;
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			getAll();
		} ]);