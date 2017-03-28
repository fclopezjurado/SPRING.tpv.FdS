tpv.controller('PopulateDBController', [ '$timeout', 'f12Service',
		function($timeout, f12Service) {
			"use strict";
			var vm = this;
			
			vm.populateDB = populateDB;
			
            vm.completed = false;
			vm.error = false;
			vm.response = "";

			function populateDB() {
				const
				delay = 2000;
				f12Service.populateDB().then(function(result) {
					// promise was fullfilled
					vm.response = "";
					vm.completed = true;
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