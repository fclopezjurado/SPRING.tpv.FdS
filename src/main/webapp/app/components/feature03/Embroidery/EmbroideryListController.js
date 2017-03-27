tpv.controller('EmbroideryListController', ['$timeout', 'f03Service',
    function ($timeout, f03Service) {
        "use strict";
        var vm = this;

        vm.completed = false;
        vm.error = false;
        vm.response;
        vm.getAll = getAll;
        vm.removeEmbroidery = removeEmbroidery;

        function getAll() {
            const
                delay = 10000;
            f03Service.getAllEmbroidery().then(function (result) {
                // promise was fullfilled
                vm.completed = true;
                vm.response = result;

                $timeout(function () {
                    vm.completed = false;
                }, delay)
            }, function (errors) {
                // handle errors
                vm.error = true;
                vm.response = errors;
                $timeout(function () {
                    vm.error = false;
                }, delay)
            });
        }

        function removeEmbroidery(id) {
            const
                delay = 10000;
            f03Service.removeEmbroidery(id).then(function (result) {
                vm.completed = true;
                vm.response = result;

                $timeout(function () {
                    vm.completed = false;
                }, delay)
            }, function (errors) {
                vm.error = true;
                vm.response = errors;
                $timeout(function () {
                    vm.error = false;
                }, delay)
            });
        }
    }]);
