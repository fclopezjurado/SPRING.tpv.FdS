tpv.controller('totalSalesController', ['$timeout', 'f14Service', '$scope',
    function ($timeout, f14Service, $scope) {
        "use strict";
        var vm = this;
        vm.completed = false;
        vm.error = false;
        vm.response = "";
        vm.inicio = "";
        vm.fin = "";
        vm.totalSales = totalSales;

        function totalSales() {
            var inicio = $("#datetimepickerInicio").data("DateTimePicker").date();
            var fin = $("#datetimepickerFin").data("DateTimePicker").date();
            if (inicio != null && fin != null) {
                const
                    delay = 2000;
                f14Service.totalSales(dateParser(inicio), dateParser(fin)).then(function (result) {
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
        }

        function dateParser(date) {
            var month = date._d.getMonth() + 1;
            date = (date._d.getDate() + "/" + month + "/" + date._d.getFullYear());
            return date;
        }
    }]);
