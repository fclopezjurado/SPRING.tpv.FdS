tpv.controller('ArticleListController', [ '$timeout', 'f03Service',
		function($timeout, f03Service) {
			"use strict";
			var vm = this;

			vm.completed = false;
			vm.error = false;
			vm.response;
			vm.getAll = getAll;
			vm.removeArticle=removeArticle;

			function getAll() {
				const
				delay = 10000;
				f03Service.getAll().then(function(result) {
					vm.completed = true;
					vm.response = result;
					
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
			
			function removeArticle(id) {
				const
				delay = 10000;
				f03Service.removeArticle(id).then(function(result) {
					vm.completed = true;
					vm.response = result;
					
					$timeout(function() {
						vm.completed = false;
					}, delay)
				}, function(errors) {
					vm.error = true;
					vm.response = errors;
					$timeout(function() {
						vm.error = false;
					}, delay)
				});
			}
		} ]);