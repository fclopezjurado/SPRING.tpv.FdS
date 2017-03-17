angular.module("tpv").controller(
		"CashierBalancingController",
		function(newCashierBalancingService, getBalancesService) {
			"use strict";

			var vm = this;

			vm.balances = getBalancesService.findAll();

			vm.successList = true;
			vm.showAlert = false;
			vm.alertMessage = '';

			vm.cashierbalancing = {
				date : new Date(),
				totalMoney : 50
			};

			vm.createCashierBalancing = function() {
				vm.balances.push(newCashierBalancingService
						.create(vm.cashierbalancing));

				console.log(vm.balances);
				//$window.location.href = '../cierrecaja';

			}

			vm.getBalances = function() {
				return [];
			}
		});
