angular.module("tpv").controller("SearchProducts",
    function ($scope, busquedaDeProductos,busquedaDeArticulos,busquedaDeEmbroidery,busquedaDeTextilePrinting) {
		"use strict";
		const SEARCH_BY_PRODUCT				= 0;
		const SEARCH_BY_ARTICLE 			= 1;
		const SEARCH_BY_EMBROIDERY			= 2;
		const SEARCH_BY_TEXTILE_PRINTING	= 3;
		
		var vm = this;
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
	 	vm.articles	= "";
		
		
		vm.onClickAdvancedSearch = () => {
	 		vm.searchVisibility = !vm.searchVisibility;
	 	}
		
        vm.changeSearchMode = function() {
        	vm.userFormError = null;
        	
        	if (parseInt(vm.searchMode) === SEARCH_BY_ARTICLE) {
        		vm.showInputForArticle 				= true;
        		vm.showInputForEmbroidery 			= false;
        		vm.showInputForTextilePrinting 		= false;
        	}
        	else if (parseInt(vm.searchMode) === SEARCH_BY_EMBROIDERY) {
        		vm.showInputForArticle 				= false;
        		vm.showInputForEmbroidery 			= true;
        		vm.showInputForTextilePrinting 		= false;
        	}
        	else if (parseInt(vm.searchMode) === SEARCH_BY_TEXTILE_PRINTING) {
        		vm.showInputForArticle 				= false;
        		vm.showInputForEmbroidery 			= false;
        		vm.showInputForTextilePrinting 		= true;
        	}
        	else {
        		vm.showInputForArticle 				= false;
        		vm.showInputForEmbroidery 			= false;
        		vm.showInputForTextilePrinting 		= false;
        	}
        }
        
        vm.getProductos = function () {
        	vm.mobile= "prueba" ;
        	if (vm.showInputForArticle ==true){
           	 vm.requestToGetProducts(busquedaDeProductos, vm.mobile);
           }
        	else if (vm.showInputForEmbroidery  ==true){
            	 vm.requestToGetProducts(busquedaDeProductos, vm.mobile);
            }
        	else if (vm.showInputForTextilePrinting ==true){
           	 vm.requestToGetProducts(busquedaDeProductos, vm.mobile);
           }
        	else {
              	 vm.requestToGetProducts(busquedaDeProductos, vm.mobile);
              }
        };
        
        vm.requestToGetProducts = function (getUserService, queryParameter) {
            var serverResponseBody;

            serverResponseBody 			= angular.fromJson(getUserService.getProducts(queryParameter));
            vm.articles			= serverResponseBody.data;
            vm.showTableProducts		= true;
            
        }
		
    });