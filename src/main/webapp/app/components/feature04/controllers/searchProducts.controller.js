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


        vm.searchProductos = {
            reference: "",
            description: "",
            minRetailPrice: "",
            maxRetailPrice: ""
        }

        vm.searchArticles = {
            reference: "",
            description: "",
            minRetailPrice: "",
            maxRetailPrice: "",
            minWholesalePrice: "",
            maxWholesalePrice: "",
            onStock: false
        }

        vm.searchTextilePriting = {
            reference: "",
            description: "",
            minRetailPrice: "",
            maxRetailPrice: "",
            type: ""
        }

        vm.searchEmbroidery = {
            reference: "",
            description: "",
            minRetailPrice: "",
            maxRetailPrice: "",
            minSquareMillimeters: "",
            maxSquareMillimeters: "",
            minStitches: "",
            maxStitches: "",
            minColors: "",
            maxColors: ""
        }
	 	
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
            var prueba = vm.searchProductos;
        	if (vm.showInputForArticle ==true){
                vm.validationArticleNumeric();
                vm.validationProductoNumeric();
                vm.passArgumentsFromProducts(vm.searchArticles);
                vm.requestToGetProducts(busquedaDeArticulos, vm.searchArticles);
           }
            else if (vm.showInputForEmbroidery == true) {
                vm.validationEmbroideryNumeric();
                vm.validationProductoNumeric();
                vm.passArgumentsFromProducts(vm.searchEmbroidery);
                vm.requestToGetProducts(busquedaDeEmbroidery, vm.searchEmbroidery);
            }
        	else if (vm.showInputForTextilePrinting ==true){
                vm.validationProductoNumeric();
                vm.passArgumentsFromProducts(vm.searchTextilePriting);
                vm.requestToGetProducts(busquedaDeTextilePrinting, vm.searchTextilePriting);
           }
        	else {
                vm.validationProductoNumeric();
                vm.requestToGetProducts(busquedaDeProductos, vm.searchProductos);
              	
              }
        };
        
        vm.requestToGetProducts = function (getProductService, queryParameter) {
            var serverResponseBody;

            serverResponseBody 			= angular.fromJson(getProductService.getProducts(queryParameter));
            vm.articles					= serverResponseBody.data;
            vm.showTableProducts		= true;
            
        }


        vm.passArgumentsFromProducts = function (model) {
            model.reference = vm.searchProductos.reference;
            model.description = vm.searchProductos.description;
            model.minRetailPrice = vm.searchProductos.minRetailPrice;
            model.maxRetailPrice = vm.searchProductos.maxRetailPrice;
        }

        vm.validationProductoNumeric = function () {
            vm.searchProductos.minRetailPrice = vm.ValidationNumeric(vm.searchProductos.minRetailPrice);
            vm.searchProductos.maxRetailPrice = vm.ValidationNumeric(vm.searchProductos.maxRetailPrice);

        }
        vm.validationArticleNumeric = function () {
            vm.searchArticles.minWholesalePrice = vm.ValidationNumeric(vm.searchArticles.minWholesalePrice);
            vm.searchArticles.maxWholesalePrice = vm.ValidationNumeric(vm.searchArticles.maxWholesalePrice);
        }


        vm.validationEmbroideryNumeric = function () {
            vm.searchEmbroidery.minSquareMillimeters = vm.ValidationNumeric(vm.searchArticles.minSquareMillimeters);
            vm.searchEmbroidery.maxSquareMillimeters = vm.ValidationNumeric(vm.searchArticles.maxSquareMillimeters);
            vm.searchEmbroidery.minStitches = vm.ValidationNumeric(vm.searchArticles.minStitches);
            vm.searchEmbroidery.maxStitches = vm.ValidationNumeric(vm.searchArticles.maxStitches);
            vm.searchEmbroidery.minColors = vm.ValidationNumeric(vm.searchArticles.minColors);
            vm.searchEmbroidery.maxColors = vm.ValidationNumeric(vm.searchArticles.maxColors);
        }

        vm.ValidationNumeric = function (parametro) {
            if ((parametro == undefined) || (parametro == null) || (parametro == ""))
                return 0;
            else
                return parametro;
        }
		
    });
