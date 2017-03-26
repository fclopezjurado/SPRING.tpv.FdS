tpv.controller('CreateArticleController', ['$timeout', 'f03Service','f02Service',
    function ($timeout, f03Service, f02Service) {
        "use strict";
        var vm = this;

        vm.completed = false;
        vm.error = false;
        vm.response;
        vm.addArticle = addArticle;
        vm.getProvider = getProvider;
        vm.providers = [];

        function addArticle() {
            const
                delay = 10000;
            f03Service.addArticle(vm.article).then(function (result) {
                vm.completed = true;
                vm.response = result;

                $timeout(function () {
                    vm.completed = false;
                }, delay)
            }, function (errors) {
                vm.error = true;
                vm.response = errors;
                $timeout(function () {
                    vm.error = false;
                }, delay)
            });
        }
        
        
        function getProvider() {
        	f02Service.getAll().then(function success(response){
        	      vm.data = response;
        	      console.log(vm.data);
        	    	    $.each(vm.data, function (i, item) {
        	    	        vm.providers.push({"id": item['id'], "company": item['company']});
        	    	    });
        	    },
        	    function error(errors){
        	      console.log(errors);
        	});
        }

    }]);
