tpv.controller('SearchTicketController', [ 'f06Service', '$timeout',
		function(f06Service, $timeout) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.ticketReference;
			vm.Search = Search;
			vm.id = "";
			vm.ticketState = "";
			vm.username = "";
			vm.created = "";
			vm.shoppings = "";

			function Search() {
				const
				delay = 2000;
				f06Service.getTicket(vm.ticketReference).then(function(result) {
					// promise was fullfilled
					vm.completed = true;
					vm.id = result.id;
					vm.ticketState = result.ticketState;
					vm.username = result.user.username;
					vm.created = result.created;
					vm.shoppings = result.shoppingList;
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function(errors) {
					// handle errors
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
		} ]);