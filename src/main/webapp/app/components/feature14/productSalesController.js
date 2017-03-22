tpv.controller('productSalesController', [ '$timeout', 'f14Service',
		function($timeout, f14Service) {
			"use strict";
			var vm = this;
			vm.productList= "";
			vm.completed = false;
			vm.error = false;
			vm.response = "";
			vm.inicio = "";
			vm.fin = "";
			vm.productSales = productSales;
			vm.productId = "";
			var chart;
			
		
			
			function productSales() {
				var inicio = $("#datetimepickerInicio").data("DateTimePicker").date();
				var fin = $("#datetimepickerFin").data("DateTimePicker").date();

				var newDate=inicio;
				
				
				if(inicio!=null && fin!=null){
					vm.notResult ="";
					const
					delay = 2000;
					f14Service.productSales(vm.productId,dateParser(inicio),dateParser(fin)).then(function(result) {
						// promise was fullfilled
						vm.completed = true;
						vm.response = result;
						
						var products = [];
						angular.forEach(result.salesOfProductList, function(value, key) {
							products.push(value.saleDate,value.totalAmount);
							
						});
						
						if(vm.response.salesOfProductList.length>0){
							chart = Highcharts.chart('container', {
							    chart: {
							        type: 'spline'
							    },
							    title: {
							        text: 'Product sales between ' + dateParser(inicio) + ' and ' + dateParser(fin) 
							    },
							    subtitle: {
							        text: vm.response.salesOfProductList[0].description
							    },
							    xAxis: {
							        type: 'datetime',
							        labels: {
							            format: '{value:%d-%m-%Y}',
							            rotation: 45,
							            align: 'left'
							        }
							    },
							    yAxis: {
							        title: {
							            text: 'Total sales of product ' +vm.response.salesOfProductList[0].description
							        },
							        min: 0
							    },
							    tooltip: {
							        headerFormat: '<b>{series.name}</b><br>',
							        pointFormat: '{point.x:%e. %b}: {point.y:,.0f} uds'
							    },
							    plotOptions: {
							        spline: {
							            marker: {
							                enabled: true
							            }
							        }
							    },
							    series: [{
							        name: vm.response.salesOfProductList[0].description + ' daily sales',
							        data: [
							        		products
							        ]
							    } ]
							});
						}else{
							vm.notResult = "Sales of " + vm.productId +" not found";
							chart.destroy();
						}
						
						
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