tpv.controller('EditAlarmController', [ '$timeout', 'f10Service',
		function($timeout, f10Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.successList = false;
			vm.successEdit = false;
			vm.alarms = null;
			vm.error = false;

			vm.loadData = loadData;
			vm.modifData = {};
			vm.modifData.newProducts = [];
			vm.selectProduct = selectProduct;
			vm.editAlarm = editAlarm;

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

			function editAlarm() {
				const
				delay = 2000;

				var selectedProducts = [];
				for (var i = 0; i < vm.modifData.newProducts.length; i++) {
					if (vm.modifData.newProducts[i].selected)
						selectedProducts.push(vm.modifData.newProducts[i]);
				}
				vm.modifData.newProducts = selectedProducts;

				f10Service.editAlarm(vm.modifData).then(function(result) {
					vm.completed = true;
					vm.successEdit = true;
					$("#modalEdicion").modal("hide");

					$timeout(function() {
						vm.completed = false;
						vm.successEdit = false;
					}, delay);
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}

			function loadData(index) {
				var alarm = vm.alarms[index];
				vm.modifData.newId = alarm.id;
				vm.modifData.newName = alarm.name;
				vm.getProducts(alarm);
				vm.modifData.newType = alarm.type;
				vm.modifData.newValue = alarm.value;
			}

			vm.getProducts = function(alarm) {
				vm.loading = true;
				f10Service.getAllProducts().then(function(result) {
					var numSelectedProducts = alarm.articleList.length;
					var productList = alarm.articleList;
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

			function selectProduct(index) {
				var bool = true;
				if (vm.modifData.newProducts[index].selected)
					bool = false;

				vm.modifData.newProducts[index].selected = bool;
			}

			getAlarms();

			$("#modalEdicion").on('hidden.bs.modal', function() {
				vm.modifData = {};
				vm.modifData.newProducts = [];
			});
		} ]);