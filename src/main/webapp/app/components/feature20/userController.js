tpv.controller('UserController', [
		'$timeout',
		'f20Service',
		function($timeout, f20Service) {
			"use strict";
			var vm = this;
			vm.users = [];
			
			vm.edit = edit;
			vm.getTemplate = getTemplate;
			vm.reset = reset;
			vm.getUsers = getUsers;
			vm.selected = {};
			
			function getUsers(){
				f20Service.getUsers().then(function(result) {
					vm.users = result.userList;
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function edit(user){
				vm.selected = angular.copy(user);
			}
			
			function getTemplate(user) {
		        if (user.mobile === vm.selected.mobile) return 'edit';
		        else return 'display';
		    }
			
			function reset() {
		        vm.selected = {};
		    }
		} ]);