(function () {
    'use strict';

    angular
        .module('tpv')
        .controller('ProductsServiceController', ProductsServiceController);

    ProductsServiceController.$inject = ['$location', 'productsServiceDataService'];

    function ProductsServiceController($location, productsServiceDataService) {
        let vm = this;

        if ('reference' in $location.search()) {
            productsServiceDataService.getTicketByReference($location.search()['reference'])
                .then(handleSuccess)
                .then(handleFailure)
        }

        function handleSuccess(response) {
            console.log("Success", response)
        }

        function handleFailure(response) {
            console.log("Failure", response)
        }
    }

})();
