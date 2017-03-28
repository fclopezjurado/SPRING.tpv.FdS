tpv.controller('CreateTicketController', [ '$scope', '$timeout', 'f06Service',
		function($scope, $timeout, f06Service) {
			"use strict";

			var vm = this;
			vm.idProduct;
			vm.amount;
			vm.discount;
			vm.description;
			vm.retailPrice;
			vm.allItems = [];
			vm.aniadirCompra = aniadirCompra;
			vm.Create = Create;
			vm.shoppings;

			function resetAll() {
				vm.shoppings = vm.allItems;
				vm.idProduct = '';
				vm.amount = '';
				vm.discount = '';
				vm.description = '';
				vm.retailPrice = '';
			}

			function aniadirCompra() {
				vm.allItems.push({
					productId : vm.idProduct,
					amount : vm.amount,
					discount : vm.discount,
					description : vm.description,
					retailPrice : vm.retailPrice
				});
				resetAll();
			}

			function Create() {
				const
				delay = 2000;

				f06Service.createTicket(vm.shoppings).then(function(result) {
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
		} ]);
