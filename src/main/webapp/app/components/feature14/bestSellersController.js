tpv.controller('bestSellersController', [ '$timeout', 'f14Service',
		function($timeout, f14Service) {
			"use strict";
			var vm = this;
			vm.productList="";
			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.inicio = "";
			vm.fin = "";
			vm.bestSellers = bestSellers;
			
			
			
			
			function bestSellers() {
				var inicio = $("#datetimepickerInicio").data("DateTimePicker").date();
				var fin = $("#datetimepickerFin").data("DateTimePicker").date();
				if(inicio!=null && fin!=null){
					const
					delay = 2000;
					f14Service.bestSellers(dateParser(inicio),dateParser(fin)).then(function(result) {
						// promise was fullfilled
						vm.completed = true;
						vm.response = result;
						vm.productList = result.bestSellersList;
						
						var products = [];
						var amounts  = []

						angular.forEach(result.bestSellersList, function(value, key) {
							products.push(value.description);
							amounts.push(value.totalAmount);
						});
						
						Highcharts.chart('container', {
							title: {
							    text: 'Top Sellers beetwen ' + dateParser(inicio) + ' and ' + dateParser(fin) 
							},
							subtitle: {
							    text: ''
							},
						
							chart: {
						        type: 'column'
						    },
						    
						    legend: {
						        enabled: false
						    },

						    xAxis: {
						        categories: products
						    },
						    yAxis: {
						        title: {
						            text: 'Amount of sales'
						        }

						    },

						    series: [{
						    	colorByPoint: true,
						    
						    	name: 'Amount of sales',
						        data: amounts
						    }]
						});
						
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
			}
			
			function dateParser(date){
				var month = date._d.getMonth()+1;
				date = (date._d.getDate()+"/"+month +"/"+ date._d.getFullYear());
				return date;
			}
		} ]);