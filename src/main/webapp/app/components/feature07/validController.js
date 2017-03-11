tpv.controller('validController', [ '$timeout', 'f07Service',
        function($timeout, f07Service) {
            "use strict";
            var vm = this;

            vm.completed = false;
            vm.error = false;
            vm.response = "";
            vm.total = total;

            function total() {
                const
                delay = 2000;
                f07Service.total().then(function(result) {
                    // promise was fullfilled
                    vm.completed = true;
                    vm.response = result.total;
                    $timeout(function() {
                        vm.completed = false;
                    }, delay)
                }, function(errors) {
                    // handle errors
                    vm.error = true;
                    vm.response = errors;
                    $timeout(function() {
                        vm.error = false;
                    }, delay)
                });
            }
        } ]);