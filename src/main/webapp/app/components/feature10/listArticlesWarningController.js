tpv.controller('ListArticlesWarningController', ['$timeout', 'f10Service',
    function ($timeout, f10Service) {

        "use strict";
        var vm = this;

        vm.send = false;
        vm.completed = false;
        vm.successList = false;
        vm.alarms = null;
        vm.error = false;

        vm.providersOpts = null;
        vm.typeFilter = '';
        vm.providerFilter = 0;
        vm.products = [];

        vm.getProductsWarning = function () {
            const
                delay = 2000;
            var typeFilter = vm.typeFilter == "" ? '' : vm.typeFilter;
            var providerFilter = vm.providerFilter == "" ? 0 : vm.providerFilter;
            f10Service.getProductsWarning(typeFilter, providerFilter).then(function (result) {
                vm.completed = true;
                vm.successList = true;
                vm.products = result;
                $timeout(function () {
                    vm.completed = false;
                });
            }, function (errors) {
                // handle errors
                vm.error = true;
                vm.response = errors;
                $timeout(function () {
                    vm.error = false;
                }, delay)
            });
        };

        vm.getProviders = function () {
            const
                delay = 2000;

            f10Service.getProviders().then(function (result) {
                vm.completed = true;
                vm.providersOpts = result.providersWrapper;
                $timeout(function () {
                    vm.completed = false;
                });
            }, function (errors) {
                // handle errors
                vm.error = true;
                vm.response = errors;
                $timeout(function () {
                    vm.error = false;
                }, delay)
            });
        };

        vm.getProviders();
        vm.getProductsWarning();

    }]);
