var tpv = angular.module("tpv", ["ngRoute", "angularCSS"]);

tpv.config(function ($routeProvider) {
    "use strict";
    $routeProvider
    	.when("/", {
    		templateUrl: "app/components/home/home.html"
        })
    	.when("/feature00/version", {
	        templateUrl: "app/components/feature00/version.html",
	        controller: "VersionController",
	        controllerAs: "vm"
        })
    	.when("/feature00/login", {
	        templateUrl: "app/components/feature00/login.html",
	        controller: "LoginController",
	        controllerAs: "vm"
        })
        .when("/feature00/registration", {
	        templateUrl: "app/components/feature00/registration.html",
	        controller: "RegistrationController",
	        controllerAs: "vm"
        })
        .when("/feature00/delete-all", {
	        templateUrl: "app/components/feature00/deleteAll.html",
	        controller: "DeleteAllController",
	        controllerAs: "vm"
        })
        //Feature 03 -01 

       .when("/feature03/feature03-1/listadoArticulos", {
	        templateUrl: "app/components/feature03/feature03-1/listadoArticulos.html",
	        controller: "",
	        controllerAs: "vm"
        })
        .when("/feature03/feature03-1/listadoBordados", {
	        templateUrl: "app/components/feature03/feature03-1/listadoBordados.html",
	        controller: "",
	        controllerAs: "vm"
        })
        .when("/feature03/feature03-1/listadoPrendaImpresa", {
	        templateUrl: "app/components/feature03/feature03-1/listadoPrendaImpresa.html",
	        controller: "",
	        controllerAs: "vm"
        })
    	.when("/feature10/crear", {
    		templateUrl: "app/components/feature10/createAlarm.html",
        	controller: "CreateAlarmController",
        	controllerAs: "vm"
    	})
    	.when("/feature10/edit", {
    		templateUrl: "app/components/feature10/editAlarm.html",
        	controller: "EditAlarmController",
        	controllerAs: "vm"
    	})
    	.when("/feature13/search-articles", {
    		templateUrl: "app/components/feature13/searchArticles.html",
    		controller: "SearchArticlesController",
    		controllerAs: "vm"
    	})
    	.when("/feature13/search-embroideries", {
    		templateUrl: "app/components/feature13/searchEmbroideries.html",
    		controller: "SearchEmbroideriesController",
    		controllerAs: "vm"
    	})
    	.when("/feature13/search-textile-printings", {
    		templateUrl: "app/components/feature13/searchTextilePrintings.html",
    		controller: "SearchTextilePrintingsController",
    		controllerAs: "vm"
    	})
        .when("/feature8.9/invoices/create", {
            templateUrl: "app/components/feature8.9/views/create_invoice.html",
            css: "app/components/feature8.9/css/style.css",
            controller: "CreateInvoiceController",
            controllerAs: "vm"
        })
        
        .when("/feature4/searchProducts", {
            templateUrl: "app/components/feature04/views/searchProducts.html",
            css: "app/components/feature04/css/style.css",
            controller: "SearchProducts",
            controllerAs: "vm"
        })
        //Feature 07        
       .when("/feature07/create", {
	        templateUrl: "app/components/feature07/create.html",
	        controller: "createController",
	        controllerAs: "vm"
        })
       .when("/feature07/search", {
	        templateUrl: "app/components/feature07/search.html",
	        controller: "searchController",
	        controllerAs: "vm"
        })
       .when("/feature07/consume", {
	        templateUrl: "app/components/feature07/consume.html",
	        controller: "consumeController",
	        controllerAs: "vm"
        })  
       .when("/feature07/validTotal", {
	        templateUrl: "app/components/feature07/validTotal.html",
	        controller: "validController",
	        controllerAs: "vm"
        })                 
        .otherwise({
            redirectTo: '/'
        });

});
