tpv.controller('BudgetController', [
		'f18Service',
		function(f18Service) {
			"use strict";
			var vm = this;
			vm.budgets = [];
			vm.detail = {};
			
			vm.getBudgets = getBudgets;
			vm.getDetails = getDetails;
			vm.createBudget = createBudget;
			vm.addProduct = addProduct;
			vm.deleteBudget = deleteBudget;
			
			function getBudgets(){
				f18Service.getBudgets().then(function(result) {
					vm.budgets = result.budgetList;
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function getDetails(budget){
				f18Service.getBudgetDetail(budget.reference).then(function(result) {
					vm.detail = result;
					console.log(vm.detail);
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function createBudget(){
				f18Service.createBudget().then(function(result) {
					vm.getBudgets();
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function addProduct(){
			}
			
			function deleteBudget(budget){
				f18Service.deleteBudget(budget.reference).then(function(result) {
					vm.getBudgets();
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
		} ]);