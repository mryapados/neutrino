var fModule = angular.module('frontApp');
fModule.controller('Testage', function ($scope, $uibModal, $frontPath) {
	
	console.log('in Testage');
	
	
	$scope.zoubida = 'ABC';

});



fModule.controller('UiAssignmentCtrl', function ($scope, $uibModal, $frontPath) {
	
	console.log('in UiAssignmentCtrl');
	
	console.log($scope.type);
	
	
	$scope.test = 'ABC';
	

	
	
	
	
	
	
	$scope.open = function(size) {

		var instance = $uibModal.open({
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement-modal.html',
			controller: 'UiAssignmentModalCtrl',
			size: size,
			resolve: {
				urlInfos: function () {
	                return {
	                	type: $scope.type, 
						pageable: {size: 10, sort: 'dateAdded,DESC', page: 0,},
					}
	            },
	            values: function(){
	                return $scope.values;
	            },
			}
		});
		instance.result.then(function(todo) {
			if (todo) {

			}
		}, function () {
	        console.log('Modal dismissed at: ' + new Date());
	    });
	};

});

fModule.controller('UiAssignmentModalCtrl', function ($scope, $uibModalInstance, urlInfos, values) {
	console.log('in UiAssignmentModalCtrl');
	console.log(urlInfos);
	console.log(values);
	
	$scope.mkUrl = function(urlInfos) {
		var url = '/neutrino/bo/blocklist/?type=' + urlInfos.type;
		var pageable = urlInfos.pageable;
		if (pageable.size) url += '&size=' + pageable.size;
		if (pageable.sort) url += '&sort=' + pageable.sort;
		if (pageable.page) url += '&page=' + pageable.page;
		$scope.urlInfos = urlInfos;
		$scope.urlMaked = url;
	};
	
	$scope.updateUrlType = function(type) {
		urlInfos.type = type;
		$scope.mkUrl(urlInfos);
	};
	$scope.updateUrlPageableSize = function(size) {
		urlInfos.pageable.size = size;
		$scope.mkUrl(urlInfos);
	};
	$scope.updateUrlPageableSort = function(sort) {
		console.log(sort);
		urlInfos.pageable.sort = sort;
		$scope.mkUrl(urlInfos);
	};
	$scope.updateUrlPageablePage = function(page) {
		urlInfos.pageable.page = page;
		$scope.mkUrl(urlInfos);
	};
	
	$scope.mkUrl(urlInfos);
	
	
	

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	$scope.save = function(form) {
		if (form.$valid) {
			$uibModalInstance.close(data);
		} else {
			$uibModalInstance.dismiss('cancel');
		}
	};

});