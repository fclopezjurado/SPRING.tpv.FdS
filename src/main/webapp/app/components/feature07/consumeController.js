tpv.controller('consumeController', [
        '$timeout',
        'f07Service',
        function($timeout, f07Service) {
            "use strict";
            var vm = this;

            vm.completed = false;
            vm.error = false;
            vm.response = "";
            vm.reference = "";
            vm.consumir = consumir;

            function consumir() {
                const
                delay = 2000;
                f07Service.consumir(vm.reference).then(
                        function(result) {
                            // promise was fullfilled
                            vm.completed = true;
                            vm.response = "Importe descontado: "+result.value;
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