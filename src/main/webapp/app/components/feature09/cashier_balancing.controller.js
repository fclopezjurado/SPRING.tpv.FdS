angular.module("tpv").controller("CashierBalancingController",
    function ($location, $filter, balancesService) {
		"use strict";
		
		var vm = this;
		
		const ERROR_FIELDS_CASHIER_BALANCING = "Debe rellenar todos los campos";

		vm.balances = [];

		vm.successList = false;
		vm.showAlert = false;
		vm.alertMessage = '';
		vm.backgroundBalance = {'background-color':'green'};
		vm.showBalance = false;
		

		vm.cashierbalancing = {
			date : new Date()
		};		
		
		vm.calculateBalance = function(){
			if(vm.cashierbalancing.total === '' || vm.cashierbalancing.change === ''  || vm.cashierbalancing.cash === '' || vm.cashierbalancing.dataphone === ''
				|| vm.cashierbalancing.total == undefined || vm.cashierbalancing.change == undefined  || vm.cashierbalancing.cash == undefined || vm.cashierbalancing.dataphone == undefined){
				vm.showAlert = true;
				vm.alertMessage = ERROR_FIELDS_CASHIER_BALANCING;
				vm.showBalance = false;
				return;
			}
			
			vm.showAlert = false;
			vm.alertMessage = '';
			
			vm.cashierbalancing.balance = vm.cashierbalancing.total - (vm.cashierbalancing.change + vm.cashierbalancing.cash + vm.cashierbalancing.dataphone);
			if(vm.cashierbalancing.balance > 0){
				vm.backgroundBalance = {'background-color':'rgba(0, 255, 0, 0.5)'};
			}else{
				vm.backgroundBalance = {'background-color':'rgba(255, 0, 0, 0.5)'};
			}
			vm.showBalance = true;
		}

		vm.createCashierBalancing = function() {			
			vm.cashierbalancing.date = $filter('date')(vm.cashierbalancing.date, "dd-MM-yyyy");
			
			balancesService.create(vm.cashierbalancing)
				.then(function(balance){
					vm.balances.push(balance);
					$location.path("/feature09/cierrecaja");
				})
				.catch(function(err) {
					vm.showAlert = true;
					vm.alertMessage = err;
				});
						
            
		}

		vm.getBalances = function() {
			return vm.requestToGetBalances(balancesService);
		}
		
		vm.requestToGetBalances = function (balancesService) {            
            angular.fromJson(balancesService.findAll().then(function(result){
            	vm.balances			= result;
            	vm.successList		= true;
            }));            
        }
		
		vm.getTotal = function(){
			let date = $filter('date')(vm.cashierbalancing.date, "dd-MM-yyyy");			
			return vm.requestToGetTotalByDate(balancesService, date);
		}
		
		vm.requestToGetTotalByDate = function (balancesService, date) {            
            angular.fromJson(balancesService.getTotal(date).then(function(result){
            	vm.cashierbalancing.total	= result || 0;
            }));            
        }
		
		vm.resetCashierBalancing = function(){
			vm.cashierbalancing = {
				date : new Date()
			};
			
			vm.getTotal();
		}
		
		vm.editCashierBalancing = function(balance){
			vm.cashierbalancing = balance;		
			if(vm.cashierbalancing.balance > 0){
				vm.backgroundBalance = {'background-color':'rgba(0, 255, 0, 0.5)'};
			}else{
				vm.backgroundBalance = {'background-color':'rgba(255, 0, 0, 0.5)'};
			}
			vm.showBalance = true;			
		}
		
		vm.saveBalance = function(){
			vm.cashierbalancing.date = $filter('date')(vm.cashierbalancing.date, "dd-MM-yyyy");		
			balancesService.update(vm.cashierbalancing)
				.then(function(balance){
					$location.path("/feature09/cierrecaja");
				})
				.catch(function(err) {
					vm.showAlert = true;
					vm.alertMessage = err;
				});
			
		}
		
		vm.getBalances();
		
		if("/feature09/cierrecaja/nuevo" == $location.path()){
			vm.getTotal();
		}
	});
