tpv.controller('EditAlarmController', [ '$timeout', 'f10Service',
		function($timeout, f10Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.alarms = null;
			vm.error = false;

			function getAlarms() {
				const
				delay = 2000;

				f10Service.getAll().then(function(result) {
					vm.completed = true;
					vm.alarms = result.alarms;
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			getAlarms();
		} ]);