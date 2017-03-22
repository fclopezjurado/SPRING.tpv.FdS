tpv.controller('productSalesController', [ '$timeout', 'f14Service',
		function($timeout, f14Service) {
			"use strict";
			var vm = this;
			vm.productList="";
			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.inicio = "";
			vm.fin = "";
			vm.productSales = productSales;
			
		
			
			function productSales() {
				var inicio = $("#datetimepickerInicio").data("DateTimePicker").date();
				var fin = $("#datetimepickerFin").data("DateTimePicker").date();
				
				

				var newDate=inicio;
				alert(new Date(newDate).getTime());
				
				if(inicio!=null && fin!=null){
					const
					delay = 2000;
					f14Service.productSales("84000003336",dateParser(inicio),dateParser(fin)).then(function(result) {
						// promise was fullfilled
						vm.completed = true;
						vm.response = result;
						
						Highcharts.chart('container', {
						    chart: {
						        type: 'spline'
						    },
						    title: {
						        text: 'Snow depth at Vikjafjellet, Norway'
						    },
						    subtitle: {
						        text: 'Irregular time data in Highcharts JS'
						    },
						    xAxis: {
						        type: 'datetime',
						        dateTimeLabelFormats: { 
						            month: '%e. %b',
						            year: '%b'
						        },
						        title: {
						            text: 'Date'
						        }
						    },
						    yAxis: {
						        title: {
						            text: 'Total sales of product'
						        },
						        min: 0
						    },
						    tooltip: {
						        headerFormat: '<b>{series.name}</b><br>',
						        pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
						    },

						    plotOptions: {
						        spline: {
						            marker: {
						                enabled: true
						            }
						        }
						    },

						    series: [{
						        name: 'Ventas del producto',
						      
						        data: [
						        	[new Date(newDate).getTime(), 0],
						        	[1490035921000, 4]
						        ]
						    } ]
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