var fModule = angular.module('frontApp');

fModule.controller('UiAssignmentCtrl', function ($scope, $uibModal, $frontPath, idProvidersFilter) {
	console.log('in UiAssignmentCtrl');
	
	$scope.open = function(size, values) {
		
		
		
		var instance = $uibModal.open({
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement-modal.html',
			controller: 'UiAssignmentModalCtrl',
			size: size,
			resolve: {
				urlInfos: function () {
	                return {
	                	type: $scope.type, 
						pageable: {size: 5, sort: 'dateAdded,DESC', page: 0,},
					}
	            },
	            values: function(){
	                return $scope[values];
	            },
	            many: function(){
	                return $scope.many;
	            },
			}
		});
		instance.result.then(function(idProviders) {
			console.log(idProviders);
			//$scope.$parent.abc = idProviders;
			
			//idProviders = [{"type":"Album","id":253},{"type":"Album","id":278}];
			console.log(idProviders);
			$scope.$parent[values] = idProvidersFilter(idProvidersFilter(idProviders), 'toArray');
		}, function () {
	        console.log('Modal dismissed at: ' + new Date());
	    });
	};

});

fModule.controller('UiAssignmentModalCtrl', function ($scope, $uibModalInstance, urlInfos, values, many) {
	console.log('in UiAssignmentModalCtrl');
	
	$scope.values = values;
	$scope.init = function(disable) {
		for(var i = 0; i < $scope.values.length; i++) {
			$scope['chk' + $scope.values[i].id] = true;
			
			//en Many, on ne peut pas enlever des objets déjà assignés en base
			if (disable === true) $scope['dsb' + $scope.values[i].id] = true;
		}
	};
	$scope.init(many);

	
	
	$scope.updateValues = function(type, id, value) {
		console.log(type, id, value);

		if (value) $scope.values.push({type: type, id: id});
		else {
			var index = null;
			for(var i = 0; i < $scope.values.length; i++) {
				if ($scope.values[i].id == id && $scope.values[i].type == type){
					index = i;
					break;
				}
			}
			if (index) $scope.values.splice(index, 1);
		}
	};
	

	$scope.mkUrl = function(urlInfos) {
		var url = '/neutrino/bo/blocklist/?type=' + urlInfos.type;
		var pageable = urlInfos.pageable;
		if (pageable.size) url += '&size=' + pageable.size;
		if (pageable.sort) url += '&sort=' + pageable.sort;
		if (pageable.page) url += '&page=' + pageable.page;
		$scope.urlInfos = urlInfos;
		$scope.urlMaked = url;
		$scope.init();
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
			$uibModalInstance.close($scope.values);
		} else {
			$uibModalInstance.dismiss('cancel');
		}
	};

});







fModule.controller('Testage', function ($scope) {
	
	$scope.test = function(test) {
		console.log(test);
		$scope.abc = [{"type":"Album","id":253},{"type":" Album","id":255}];
	};
});