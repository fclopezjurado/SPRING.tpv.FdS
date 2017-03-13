tpv.controller('CreateAlarmController', [ '$timeout', 'f10Service',
		function($timeout, f10Service) {
			"use strict";
			var vm = this;

			vm.name;

			vm.products = [];

			vm.selection = [];
			
			vm.loading = true;
			
			vm.errorLoading = false;
			
			vm.type;
			
			vm.number;

			vm.completed;
			
			vm.send = false;
			
			vm.createAlarm = function() {
				const delay = 2000;
				f10Service.createAlarm(vm.name, vm.selection, vm.type, vm.number).then(function(result) {
					// promise was fullfilled
					vm.completed = true;
					vm.send = true;
					$timeout(function() {
						vm.send = false;
					}, delay);
				}, function(errors) {
					// handle errors
					vm.completed = false;
					vm.send = true;
					$timeout(function() {
						vm.send = false;
					}, delay);
				});
			};
			
			vm.toggleSelection = function(productName) {
			    var idx = vm.selection.indexOf(productName);

			    // Is currently selected
			    if (idx > -1) {
			      vm.selection.splice(idx, 1);
			    }

			    // Is newly selected
			    else {
			      vm.selection.push(productName);
			    }
			  };
			  
			vm.getProducts = function() {
				vm.loading = true;
				f10Service.getAllProducts().then(function(result){
					$.each(result.products, function( index, value ) {
						  vm.products.push(value.description);
						});
					vm.loading = false;
					vm.errorLoading = false;
				}, function(errors){
					vm.loading = false;
					vm.errorLoading = true;
				});
			};
			vm.getProducts();
		} ]);