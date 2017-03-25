tpv.controller('productsFamilyController', [ '$timeout', 'f05Service',
		function($timeout, f05Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.response1;
			vm.getAll=getAll;
			vm.getFamilyName=getFamilyName;

			function getAll() {
				const
				delay = 10000;
				f05Service.getAll().then(function(result) {
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
			
			function getFamilyName(name) {
				const
				delay = 10000;
				f05Service.getFamilyName(name).then(function(result) {
					vm.completed = true;
					vm.response1 = result;
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response1 = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			
			
		}
]);