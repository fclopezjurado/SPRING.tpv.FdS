tpv.controller('UpdateTextilePrintingController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.TextilePrinting=updateTextilePrinting;

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
		
		} ]);