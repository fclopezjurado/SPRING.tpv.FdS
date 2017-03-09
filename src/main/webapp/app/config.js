var tpv = angular.module("tpv", ["ngRoute"]);

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
        	controller: "createAlarmController.js",
        	controllerAs: "vm"
    	})
    	.when("/feature10/edit", {
    		templateUrl: "app/components/feature10/editAlarm.html",
        	controller: "EditAlarmController",
        	controllerAs: "vm"
    	})
        
         .when("/feature04/search-articles", {
             templateUrl: "app/components/feature04/searchArticles.html",
             controller: "SearchArticlesController",
             controllerAs: "vm"
         })
        .when("/feature04/search-embroideries", {
             templateUrl: "app/components/feature04/searchEmbroideries.html",
             controller: "SearchEmbroideriesController",
             controllerAs: "vm"
         })
         .when("/feature04/search-textile-printings", {
             templateUrl: "app/components/feature04/searchTextilePrintings.html",
             controller: "SearchTextilePrintingsController",
              controllerAs: "vm"
          })
        
        .otherwise({
            redirectTo: '/'
        });

});
