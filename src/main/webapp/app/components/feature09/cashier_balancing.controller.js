angular.module("tpv").controller(
		"CashierBalancingController",
    function ($location, newCashierBalancingService, getBalancesService) {
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
                $location.path("/feature09/cierrecaja");

			}

			vm.getBalances = function() {
				return [];
			}
		});
