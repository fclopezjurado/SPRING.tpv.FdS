tpv.controller('BudgetController', [
		'f18Service',
		'f03Service',
		function(f18Service, f03Service) {
			"use strict";
			var vm = this;
			vm.budgets = [];
			vm.detail = {};
			vm.products = [];
			vm.productAmount = 1;
			
			vm.getBudgets = getBudgets;
			vm.getDetails = getDetails;
			vm.createBudget = createBudget;
			vm.addProduct = addProduct;
			vm.deleteBudget = deleteBudget;
			vm.getProducts = getProducts;
			
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
					vm.getProducts();
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
			
			function addProduct(budget){
				f18Service.addProduct(budget.reference, vm.productId, vm.productAmount).then(function(result) {
					vm.getDetails(budget);
					vm.getBudgets();
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
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
			
			function getProducts(){
				f03Service.getAll().then(function(result) {
					console.log(result);
					vm.products = result;
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
		} ]);