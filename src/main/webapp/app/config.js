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
       .when("/feature03/Articles/ArticleList", {
	        templateUrl: "app/components/feature03/Articles/ArticleList.html",
	        controller: "ArticleListController",
	        controllerAs: "vm"
        })
        .when("/feature03/Embroidery/EmbroideryList", {
            templateUrl: "app/components/feature03/Embroidery/EmbroideryList.html",
            controller: "EmbroideryListController",
            controllerAs: "vm"
        })
             .when("/feature03/TextilePrinting/TextilePrintingList", {
            templateUrl: "app/components/feature03/TextilePrinting/TextilePrintingList.html",
            controller: "TextilePrintingListController",
            controllerAs: "vm"
        })
        
        .when("/feature10/list", {
            templateUrl: "app/components/feature10/listAlarms.html",
            controller: "ListAlarmsController",
            controllerAs: "vm"
        })
    	.when("/feature10/list/articles", {
    		templateUrl: "app/components/feature10/listArticlesWarning.html",
        	controller: "ListArticlesWarningController",
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
        .when("/feature8.10/invoices/details", {
            templateUrl: "app/components/feature8.10/views/get_invoice.html",
            css: "app/components/feature8.10/css/style.css",
            controller: "GetInvoiceController",
            controllerAs: "vm"
        })
        .when("/feature20/users", {
            templateUrl: "app/components/feature20/users.html",
            controller: "UserController",
            controllerAs: "vm"
        })
        //Feature 09
        .when("/feature09/cierrecaja/nuevo", {
            templateUrl: "app/components/feature09/new_cashier_balancing.html",
            controller: "CashierBalancingController",
            controllerAs: "vm"
        })
        .when("/feature11/ticket", {
            templateUrl: "app/components/feature11/ticket.html",
            controller: "ProductsServiceController",
            controllerAs: "vm"
        })
        .when("/feature06/createTicket", {
	        templateUrl: "app/components/feature06/createTicket.html",
	        controller: "CreateTicketController",
	        controllerAs: "vm"
        })
        .when("/feature06/searchTicket", {
	        templateUrl: "app/components/feature06/searchTicket.html",
	        controller: "SearchTicketController",
	        controllerAs: "vm"
        })
        .when("/feature06/editTicket", {
	        templateUrl: "app/components/feature06/editTicket.html",
	        controller: "EditTicketController",
	        controllerAs: "vm"
        })
        //Feature 14
        .when("/feature14/totalSales", {
            templateUrl: "app/components/feature14/totalSales.html",
            controller: "totalSalesController",
            controllerAs: "vm"
        })
        .when("/feature14/bestSellers", {
            templateUrl: "app/components/feature14/bestSellers.html",
            controller: "bestSellersController",
            controllerAs: "vm"
        })
        .when("/feature14/bestSellers", {
            templateUrl: "app/components/feature14/productSalesDevelopment.html",
            controller: "productSalesDevelopmentController",
            controllerAs: "vm"
        })
        .otherwise({
            redirectTo: '/'
        });

});
