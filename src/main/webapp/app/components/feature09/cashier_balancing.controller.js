angular.module("tpv").controller("CashierBalancingController",
    function ($location, $filter, newCashierBalancingService, getBalancesService) {
		"use strict";

		var vm = this;

		vm.balances = [];

		vm.successList = false;
		vm.showAlert = false;
		vm.alertMessage = '';

		vm.cashierbalancing = {
			date : new Date(),
			totalMoney : 50
		};

		vm.createCashierBalancing = function() {
			vm.cashierbalancing.date = $filter('date')(vm.cashierbalancing.date, "dd-MM-yyyy");
			vm.balances.push(newCashierBalancingService.create(vm.cashierbalancing));

			//console.log(vm.balances);
            //$location.path("/feature09/cierrecaja");

		}

		vm.getBalances = function() {
			return vm.requestToGetBalances(getBalancesService);
		}
		
		vm.requestToGetBalances = function (getBalancesService) {            
            angular.fromJson(getBalancesService.findAll().then(function(result){
            	vm.balances			= result;
            	vm.successList		= true;
            }));            
        }
		
		vm.getBalances();
	});
