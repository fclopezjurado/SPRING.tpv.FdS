tpv.controller('SearchTextilePrintingsController', function($route, f13Service) {
 	"use strict";
 	
 	var vm = this;
 	
 	vm.textilePrintings = f13Service.getTextilePrintings();
 	vm.exactRetailPrice = 0;
 	vm.minRetailPrice = 0;
 	vm.maxRetailPrice = 0;
 	vm.searchVisibility = false;
 	vm.sortType = "reference";
 	vm.sortReverse = false;
 	
 	vm.filterExactRetailPrice = (prod) => {
 		return vm.exactRetailPrice == 0 || vm.exactRetailPrice == null || prod.retailPrice.toString().match(vm.exactRetailPrice.toString()) != null;
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
 	
 	vm.onClickAdvancedSearch = () => {
 		vm.searchVisibility = !vm.searchVisibility;
 	}
 	
 	vm.clearFilters = () => {
 		$route.reload();
 	}
 		
 });