tpv.controller('ListAlarmsController', [
		'$window',
		'$timeout',
		'f10Service',
		function($window, $timeout, f10Service) {
			"use strict";
			var vm = this;

			vm.send = false;
			vm.completed = false;
			vm.successList = false;
			vm.alarms = null;
			vm.error = false;

			vm.successCreate = false;
			vm.successEdit = false;
			vm.successRemove = false;
			vm.modifData = {};
			vm.modifData.newProducts = [];

			function getAlarms() {
				const
				delay = 2000;

				f10Service.getAll().then(function(result) {
					vm.completed = true;
					vm.successList = true;
					vm.alarms = result.alarms;
					$timeout(function() {
						vm.completed = true;
					});
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			
			vm.getProducts = function(alarm) {
				vm.loading = true;
				f10Service.getAllProducts().then(function(result) {
					var numSelectedProducts = 0;
					var productList = [];
					if(alarm) {
						numSelectedProducts = alarm.articleList.length;
						productList = alarm.articleList;
					}
					$.each(result.products, function(index, value) {
						for (var i = 0; i < numSelectedProducts; i++) {
							if (productList[i]) {
								if (productList[i].id === value.id) {
									value.selected = true;
									break;
								} else {
									value.selected = false;
								}
							} else {
								value.selected = false;
							}
						}
						vm.modifData.newProducts.push(value);
					});

					vm.loading = false;
					vm.errorLoading = false;
				}, function(errors) {
					vm.loading = false;
					vm.errorLoading = true;
				});
			};
			
			vm.createAlarm = function() {
				const delay = 2000;
				var copy = $.extend(true, {}, vm.modifData);
				copy.newProducts = [];
				$.each(vm.modifData.newProducts, function (index, value){
					if(value.selected){
						copy.newProducts.push(value);
					}
				});
				f10Service.createAlarm(copy).then(function(result) {
					vm.send = true;
					vm.completed = true;
					vm.successCreate = true;
					$("#modalCreacion").modal("hide");
					$timeout(function() {
						vm.send = false;
						vm.completed = false;
						vm.successCreate = false;
						$window.location.reload();
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


			vm.editAlarm = function() {
				const
				delay = 2000;

				var selectedProducts = [];
				for (var i = 0; i < vm.modifData.newProducts.length; i++) {
					if (vm.modifData.newProducts[i].selected)
						selectedProducts.push(vm.modifData.newProducts[i]);
				}
				vm.modifData.newProducts = selectedProducts;

				f10Service.editAlarm(vm.modifData).then(function(result) {
					vm.send = true;
					vm.completed = true;
					vm.successEdit = true;
					$("#modalEdicion").modal("hide");
					$timeout(function() {
						vm.send = false;
						vm.completed = false;
						vm.successEdit = false;
						$window.location.reload();
					}, delay);
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			};
			
			vm.loadData = function(index) {
				var alarm = vm.alarms[index];
				vm.modifData.newId = alarm.id;
				vm.modifData.newName = alarm.name;
				vm.getProducts(alarm);
				vm.modifData.newType = alarm.type;
				vm.modifData.newValue = alarm.value;
			};

			vm.selectProduct = function(index) {
				var bool = true;
				if (vm.modifData.newProducts[index].selected)
					bool = false;

				vm.modifData.newProducts[index].selected = bool;
			};
			
			vm.removeAlarm = function(index) {
				const delay = 2000;
				f10Service.removeAlarm(vm.alarms[index].id).then(function(result) {
					vm.send = true;
					vm.completed = true;
					vm.successRemove = true;
					vm.alarms.splice(index, 1);
					$timeout(function() {
						vm.send = false;
						vm.completed = false;
						vm.successRemove = false;
						//$window.location.reload();
					}, delay);
				}, function(errors) {
					// handle errors
					vm.errorRemove = true;
					vm.response = errors;
					$timeout(function() {
						vm.errorRemove = false;
					}, delay)
				});
			};

			getAlarms();

			$("#modalCreacion,#modalEdicion").on('hidden.bs.modal', function() {
				vm.modifData = {};
				vm.modifData.newType = "WARNING";
				vm.modifData.newProducts = [];
			});
		} ]);