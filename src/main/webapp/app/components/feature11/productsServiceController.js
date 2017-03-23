(function () {
    'use strict';

    angular
        .module('tpv')
        .controller('ProductsServiceController', ProductsServiceController);

    ProductsServiceController.$inject = ['$location', 'productsServiceDataService'];

    function ProductsServiceController($location, productsServiceDataService) {
        let vm = this;

        const ticketStates = [
            'OPENED',
            'STARTED',
            'CLOSED'
        ];

        vm.ticket = null;
        vm.reference = null;
        vm.ticketStates = ticketStates;
        vm.ticketProgress = 0;

        vm.loading = true;
        vm.error = '';
        vm.errorCode = null;

        vm.referenceInput = '';
        vm.submitReference = submitReference;

        if ('reference' in $location.search()) {
            vm.reference = $location.search()['reference'];
            productsServiceDataService.getTicketByReference(vm.reference)
                .then(handleSuccess, handleFailure)
        }

        function handleSuccess(response) {
            vm.ticket = response['data'];
            vm.ticketProgress = (vm.ticketStates.indexOf(vm.ticket['ticketState']) + 1) / vm.ticketStates.length * 100;
            vm.loading = false;
        }

        function handleFailure(response) {
            vm.errorCode = response['status'];
            vm.error = response['data']['description'];
            vm.loading = false;
        }

        function submitReference() {
            $location.search('reference', vm.referenceInput);
        }
    }

})();
