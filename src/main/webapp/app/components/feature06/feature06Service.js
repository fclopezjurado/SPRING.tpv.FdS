tpv
		.service(
				'f06Service',
				[
						'$http',
						'$q',
						function($http, $q) {
							"use strict";

							const
							urlBase = "http://localhost:8080/SPRING.tpv.FdS.1.2.0-SNAPSHOT/api/v0";

							this.request = function(config) {
								let
								deferred = $q.defer();
								$http(config)
										.then(
												function(response) {
													deferred
															.resolve(response.data);
												},
												function(response) {
													let
													errorMsg;
													if (response.data.error === undefined) {
														errorMsg = "";
													} else {
														errorMsg = " --- "
																+ response.data.error
																+ ":"
																+ response.data.description;
													}
													deferred
															.reject("Error ("
																	+ response.status
																	+ ":"
																	+ response.statusText
																	+ ")"
																	+ errorMsg);
												});
								return deferred.promise;
							}

							this.getTicket = function(ticketReference) {
								let
								config = {
									method : 'GET',
									url : urlBase + "/tickets/"
											+ ticketReference,
								};

								return this.request(config);
							};

							this.createTicket = function(shoppings) {
								let
								config = {
									method : 'POST',
									url : urlBase + "/tickets",
									data : {
										'shoppingList' : shoppings
									}
								};
								return this.request(config);
							};

							this.updateTicket = function(products, type, number) {
								let
								config = {
									method : 'PUT',
									url : urlBase + "/tickets",
									data : {
										'products' : products,
										'type' : type,
										'number' : number
									}
								};
								return this.request(config);
							};
						} ]);
