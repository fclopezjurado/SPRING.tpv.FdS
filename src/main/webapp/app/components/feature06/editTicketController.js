tpv.controller('EditTicketController', ['$timeout', 'f06Service',
    function ($timeout, f06Service) {
        "use strict";

        var vm = this;
        vm.ticketReference;
        vm.completed = false;
        vm.error = false;
        vm.ticketState;
        vm.shoppings;
        vm.response;
        vm.searchTicket = searchTicket;
        vm.previousState;
        vm.ticketID;
        vm.editTicket = editTicket;
        
        function searchTicket() {
        	const
			delay = 2000;
			f06Service.getTicket(vm.ticketReference).then(function(result) {
				// promise was fullfilled
				vm.completed = true;
				vm.previousState = result.ticketState;
		        vm.shoppings = result.shoppingList;
		        vm.ticketID = result.id;
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

        function editTicket() {
        	const
			delay = 2000;

			f06Service.updateTicket(vm.ticketID, vm.shoppings, vm.ticketState).then(function(result) {
				console.log(result);
				$timeout(function() {
					vm.completed = false;
				}, delay)
			}, function(errors) {
				// handle errors
				$timeout(function() {
					vm.error = false;
				}, delay)
			});
        }
    }]);
