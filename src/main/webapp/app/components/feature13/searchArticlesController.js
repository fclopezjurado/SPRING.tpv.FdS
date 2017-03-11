tpv.controller('SearchArticlesController', function($route, f13Service) {
 	"use strict";
 	
 	var vm = this;
 	
 	vm.articles = f13Service.getArticles();
 	vm.exactRetailPrice = 0;
 	vm.minRetailPrice = 0;
 	vm.maxRetailPrice = 0;
 	vm.exactWholesalePrice = 0;
 	vm.minWholesalePrice = 0;
 	vm.maxWholesalePrice = 0;
 	vm.showOnlyOnStock = false;
 	vm.searchVisibility = false;
 	vm.sortType = "reference";
 	vm.sortReverse = false;
 	
 	vm.filterExactRetailPrice = (prod) => {
 		return vm.exactRetailPrice == 0 || vm.exactRetailPrice == null || prod.retailPrice.toString().match(vm.exactRetailPrice.toString()) != null;
 	}
 	
 	vm.filterExactWholesalePrice = (prod) => {
 		return vm.exactWholesalePrice == 0 || vm.exactWholesalePrice == null || prod.wholesalePrice.toString().match(vm.exactWholesalePrice.toString()) != null;
 	}
 	
 	vm.filterOnStock = (prod) => {
 		return vm.showOnlyOnStock == false || prod.stock > 0;
 	}
 	
 	vm.filterRetailPrice = (prod) => {
 		if (vm.minRetailPrice > 0 && vm.maxRetailPrice > 0){
 			return (prod.retailPrice >= vm.minRetailPrice && prod.retailPrice <= vm.maxRetailPrice);
 		} else if (vm.minRetailPrice > 0){
 			return (prod.retailPrice >= vm.minRetailPrice);
 		} else if (vm.maxRetailPrice > 0){
 			return (prod.retailPrice <= vm.maxRetailPrice);
 		} else {
 			return true;
 		}
 	}
 	
 	vm.filterWholesalePrice = (prod) => {
 		if (vm.minWholesalePrice > 0 && vm.maxWholesalePrice > 0){
 			return (prod.wholesalePrice >= vm.minWholesalePrice && prod.wholesalePrice <= vm.maxWholesalePrice);
 		} else if (vm.minWholesalePrice > 0){
 			return (prod.wholesalePrice >= vm.minWholesalePrice);
 		} else if (vm.maxWholesalePrice > 0){
 			return (prod.wholesalePrice <= vm.maxWholesalePrice);
 		} else {
 			return true;
 		}
 	}
 	
 	vm.onClickAdvancedSearch = () => {
 		vm.searchVisibility = !vm.searchVisibility;
 	}
 	
 	vm.clearFilters = () => {
 		$route.reload();
 	}
 		
 }); 