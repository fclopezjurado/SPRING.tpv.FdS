tpv.controller('UserController', [
		'$timeout',
		'f20Service',
		function($timeout, f20Service) {
			"use strict";
			var vm = this;
			vm.users = [
				{
					id: 1,
					mobile: '666 666 666',
					username: 'Jorge',
					dni: '12345678Z',
					address: 'Calle Génova 23',
					email: 'mimail@alumnos.urjc.es',
					registrationDate: '01/12/2004',
					active: true
				}
			];
			
			vm.edit = edit;
			vm.getTemplate = getTemplate;
			vm.reset = reset;
			vm.getUsers = getUsers;
			vm.selected = {};
			
			function getUsers(){
				const delay = 2000;

				f20Service.getUsers().then(function(result) {
					$timeout(function() {
						vm.users = result;
					}, delay)
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
			
			function edit(user){
				vm.selected = angular.copy(user);
			}
			
			function getTemplate(user) {
		        if (user.id === vm.selected.id) return 'edit';
		        else return 'display';
		    }
			
			function reset() {
		        vm.selected = {};
		    }
		} ]);