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
			
			function getDetails(){
				f18Service.getBudgetDetail().then(function(result) {
					vm.detail = result.budget;
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function createBudget(){
			}
			
			function addProduct(){
			}
			
			function deleteBudget(){
				
			}
		} ]);