angular.module("tpv").controller(
		"CashierBalancingController",
		function(newCashierBalancingService) {
			"use strict";

			var vm = this;

			var balances = [];

			vm.cashierbalancing = {
				date : new Date(),
				totalMoney : 50
			};

			vm.createCashierBalancing = function() {
				balances.push(angular.fromJson(newCashierBalancingService
						.create(vm.cashierbalancing)));

				console.log(balances);
			}
		});
