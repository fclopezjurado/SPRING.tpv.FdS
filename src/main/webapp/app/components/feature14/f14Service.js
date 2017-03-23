tpv.service('f14Service', ['$http', '$q', function ($http, $q) {
    "use strict";

    const urlBase = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0";

    this.request = function (config) {
        let deferred = $q.defer();
        $http(config).then(function (response) {
            deferred.resolve(response.data);
        }, function (response) {
            let errorMsg;
            if (response.data.error === undefined) {
                errorMsg = "";
            } else {
                errorMsg = " --- " + response.data.error + ":" + response.data.description;
            }
            deferred.reject(
                "Error (" + response.status + ":" + response.statusText + ")" + errorMsg);
        });
        return deferred.promise;
    }

    this.totalSales = function (inicio, fin) {
        let config = {
            method: 'POST',
            url: urlBase + "/totalSales",
            data: {'inicio': inicio, 'fin': fin}
        };
        return this.request(config);
    }

    this.bestSellers = function (inicio, fin) {
        let config = {
            method: 'POST',
            url: urlBase + "/BestSellers",
            data: {'inicio': inicio, 'fin': fin}
        };
        return this.request(config);
    }


}]);
