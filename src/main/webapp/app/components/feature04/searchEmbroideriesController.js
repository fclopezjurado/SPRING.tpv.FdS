tpv.controller('SearchEmbroideriesController', function($route, f04Service) {
	"use strict";
	
	var vm = this;
	
	vm.embroideries = f04Service.getEmbroideries();
	vm.exactRetailPrice = 0;
	vm.minRetailPrice = 0;
	vm.maxRetailPrice = 0;	
	vm.exactStitches = 0;
	vm.minStitches = 0;
	vm.maxStitches = 0;
	vm.exactColors = 0;
	vm.minColors = 0;
	vm.maxColors = 0;
	vm.exactSquareMillimeters = 0;
	vm.minSquareMillimeters = 0;
	vm.maxSquareMillimeters = 0;
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
	
	vm.filterExactStitches = (prod) => {
		return vm.exactStitches == 0 || vm.exactStitches == null || prod.stitches.toString().match(vm.exactStitches.toString()) != null;
	}
	
	vm.filterStitches = (prod) => {
		if (vm.minStitches > 0 && vm.maxStitches > 0){
			return (prod.stitches >= vm.minStitches && prod.stitches <= vm.maxStitches);
		} else if (vm.minStitches > 0){
			return (prod.stitches >= vm.minStitches);
		} else if (vm.maxStitches > 0){
			return (prod.stitches <= vm.maxStitches);
		} else {
			return true;
		}
	}
	
	vm.filterExactColors = (prod) => {
		return vm.exactColors == 0 || vm.exactColors == null || prod.colors.toString().match(vm.exactColors.toString()) != null;
	}
 	
 vm.filterColors = (prod) => {
		if (vm.minColors > 0 && vm.maxColors > 0){
			return (prod.colors >= vm.minColors && prod.colors <= vm.maxColors);
		} else if (vm.minColors > 0){
			return (prod.colors >= vm.minColors);
		} else if (vm.maxColors > 0){
			return (prod.colors <= vm.maxColors);
		} else {
			return true;
		}
	}
	
	vm.filterExactSquareMillimeters = (prod) => {
		return vm.exactSquareMillimeters == 0 || vm.exactSquareMillimeters == null || prod.squareMillimeters.toString().match(vm.exactSquareMillimeters.toString()) != null;
	}
	
	vm.filterSquareMillimeters = (prod) => {
		if (vm.minSquareMillimeters > 0 && vm.maxSquareMillimeters > 0){
			return (prod.colors >= vm.minSquareMillimeters && prod.squareMillimeters <= vm.maxSquareMillimeters);
		} else if (vm.minSquareMillimeters > 0){
			return (prod.squareMillimeters >= vm.minSquareMillimeters);
		} else if (vm.maxSquareMillimeters > 0){
			return (prod.squareMillimeters <= vm.maxSquareMillimeters);
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

