tpv.controller('EditTicketController', ['$scope', '$timeout', 'f06Service',
    function ($scope, $timeout, f06Service) {
        "use strict";

        var vm = this;
        vm.reference;
        vm.ticketState;

        function searchTicket() {
            const
                delay = 2000;
            f06Service.getTicket().then(function (result) {
                // promise was fullfilled
                vm.completed = true;
                vm.response = result.version;
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

        function editTicket() {

        }
    }]);
