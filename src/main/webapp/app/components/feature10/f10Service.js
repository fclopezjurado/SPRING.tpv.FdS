tpv.service('f10Service', ['$http', '$q', function ($http, $q) {
	"use strict";

	const urlBase="http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0";

	this.request = function(config) {
		let deferred = $q.defer();
		$http(config).then(function (response) {
			deferred.resolve(response.data);
		}, function (response){
			let errorMsg;
			if(response.data.error === undefined) {
				errorMsg="";
			}else{
				errorMsg = " --- " + response.data.error + ":" + response.data.description;
			}
			deferred.reject( 
					"Error (" + response.status + ":" + response.statusText + ")" + errorMsg );
		});
		return deferred.promise;	   
	}

	this.getAll = function() {
		let config = {
				method: 'GET',
				url: urlBase + "/alarms",
		};

		return this.request(config); 
	};

	this.createAlarm = function(modifData) {
		console.log(modifData);
		let config = {
				method: 'POST',
				url: urlBase + "/alarms",
				data:{'name': modifData.newName, 'productsList': modifData.newProducts, 'type': modifData.newType, 'numProducts': modifData.newValue}
		};
		return this.request(config);
	};

	this.editAlarm = function(modifData) {
		let config = {
				method: 'PUT',
				url: urlBase + "/alarms",
				data:{'id': modifData.newId, 'name': modifData.newName, 'productsList': modifData.newProducts, 'type': modifData.newType, 'numProducts': modifData.newValue}
		};
		return this.request(config);
	};

	this.removeAlarm = function(alarmId) {
		let config = {
				method: 'DELETE',
				url: urlBase + "/alarms/" + alarmId
		};
		return this.request(config);
	}

	this.getAllProducts = function() {
		let config = {
				method: 'GET',
				url: urlBase + "/mock_products"
		}

		return this.request(config);
	};
}]);