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
			vm.updateUser = updateUser;
			vm.deleteUser = deleteUser;
			vm.setUser = setUser;
			vm.user = {};
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
			
			function updateUser() {
				f20Service.updateUser(vm.selected).then(function(result) {
					vm.selected = {};
					vm.getUsers();
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function deleteUser(user) {
				f20Service.deleteUser(user.mobile).then(function(result) {
					vm.selected = {};
					vm.getUsers();
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					vm.error = false;
				});
			}
			
			function reset() {
		        vm.selected = {};
		    }
			
			function setUser(user) {
				vm.user = user;
			}
		} ]);