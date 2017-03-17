tpv.controller('searchController', [
        '$timeout',
        'f07Service',
        function($timeout, f07Service) {
            "use strict";
            var vm = this;

            vm.completed = false;
            vm.error = false;
            vm.response = "";
            vm.reference = "all";
            vm.consultar = consultar;
            
            function consultar() {
                const
                delay = 2000;
                f07Service.consultar(vm.reference).then(
                        function(result) {
                            // promise was fullfilled
                            vm.completed = true;
                            vm.response = "Info recupearada correctamente";
                            vm.vales = result;
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