tpv.controller('productsFamilyController', [ '$timeout', 'f05Service',
		function($timeout, f05Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.getFamily=getFamily;
			vm.getFamilyName=getFamilyName;

			function getFamily() {
				const
				delay = 2000;
				f05Service.getFamily().then(function(result) {
					vm.completed = true;
					vm.response = result.getFamily;
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
			
			function getFamilyName(name) {
				const
				delay = 2000;
				f05Service.getFamilyName().then(function(result) {
					vm.completed = true;
					vm.response = result.getFamilyName;
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
			
			
		}
]);