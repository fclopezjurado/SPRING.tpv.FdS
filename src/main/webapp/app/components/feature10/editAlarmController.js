tpv.controller('EditAlarmController', [ '$timeout', 'f10Service',
		function($timeout, f10Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.successList = false;
			vm.successEdit = false;
			vm.alarms = null;
			vm.error = false;
			vm.editAlarm = editAlarm;

			function getAlarms() {
				const
				delay = 2000;

				f10Service.getAll().then(function(result) {
					vm.completed = true;
					vm.successList = true;
					vm.alarms = result.alarms;
					$timeout(function() {
						vm.completed = true;
					});
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}

			function editAlarm(alarmId, alarmName, alarmType, alarmUnits) {
				const
				delay = 2000;
			
				f10Service.editAlarm(alarmId, alarmName, alarmType, alarmUnits).then(function(result) {
					vm.completed = true;
					vm.successEdit = true;
					$timeout(function() {
						vm.completed = false;
						vm.successEdit = false;
					}, delay);
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