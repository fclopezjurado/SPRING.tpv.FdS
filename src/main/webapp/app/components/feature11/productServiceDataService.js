(function () {
    'use strict';

    angular
        .module('tpv')
        .service('productsServiceDataService', productsServiceDataService);

    productsServiceDataService.$inject = ['$http'];

    function productsServiceDataService($http) {

        const urlBase = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0";

        this.getTicketByReference = getTicketByReference;

        function getTicketByReference(reference) {
            return $http({
                method: 'GET',
                url: urlBase + "/tickets?reference=" + reference,
            })
        }
    }

})();
