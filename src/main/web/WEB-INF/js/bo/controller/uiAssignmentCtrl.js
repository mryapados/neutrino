//var fModule = angular.module('frontApp');
//
//fModule.controller('UiAssignmentCtrl', function ($scope, $uibModal, $frontPath, idProvidersFilter, $http, $parse) {
//	console.log('in UiAssignmentCtrl');
//	
//	$scope.open = function(size) {
//		var instance = $uibModal.open({
//			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement-modal.html',
//			controller: 'UiAssignmentModalCtrl',
//			size: size,
//			resolve: {
//				urlInfos: function () {
//	                return {
//	                	type: $scope.type, 
//	                	field: $scope.field, 
//	                	id: $scope.id, 
//						pageable: {size: 5, page: 0,},
//					}
//	            },
//	            values: function(){
//	                return $scope.values;
//	            },
//	            disablePreChecked: function(){
//	                return $scope.disablePreChecked;
//	            },
//			}
//		});
//		instance.result.then(function(idProviders) {
//			console.log('open');
//			console.log('idProviders = ' + idProviders);
//			
//			
//			
////			$parse($scope.model).assign($scope, idProviders);
////			$parse($scope.model).assign($scope.$parent, idProviders);
////			
////			
////			console.log('mm = ' + $scope.model);
//			
//			$scope[$scope.model] = idProviders;
//			$scope.$parent[$scope.model] = idProviders;
//			
//			
//			$scope.model = 'rrrr';
//			$scope['model'] = 'rrrrrrr';
////			
////			$scope.$parent[values] = idProvidersFilter(idProvidersFilter(idProviders), 'toArray');
//			//$scope.resultValues = $scope.$parent[values];
//			
////			getIdProvidersText($scope.$parent[values], 5).then(function(response) {
////				console.log(response.data);
////				$scope.resultText = response.data;
////			})
////			.catch(function(error){
////				console.log(error.status);
////				console.log(error);
////			});
//			
//		}, function () {
//	        console.log('Modal dismissed at: ' + new Date());
//	    });
//	};
//	
//	
//	
////	var getIdProvidersText = function(idproviders, max) {
////
////		if (idproviders && idproviders.length > 0){
////			if (!max) max = idproviders.length;
////			else if (idproviders.length < max) max = idproviders.length;
////			
////			var type = idproviders[0].type;
////			var ids = [];
////			for(var i = 0; i < max; i++) {
////				ids.push(idproviders[i].id);
////			}
////		}
////
////		return $http.get($frontPath.URL_SERVER_REST + 'bo/objects/' + type, {params:{'id': ids}});
////
////		
////	};
//	
//	
//
//	
//
//});
//
//fModule.controller('UiAssignmentModalCtrl', function ($scope, $uibModalInstance, urlInfos, values, disablePreChecked) {
//	console.log('in UiAssignmentModalCtrl');
//	console.log('values = ' + values);
//	
//	values = 'final';
//	
////	if (!values) console.log('ttttttttttttttttttttttttttttttttttt');
////	
////	
////	if (!values) values = [];
////	
////	$scope.values = values;
////
////	$scope.init = function(disable) {
////		if ($scope.values) {
////			for(var i = 0; i < $scope.values.length; i++) {
////				$scope.lastCheckedId = $scope.values[i].id;
////				$scope['chk' + $scope.values[i].id] = true;
////				
////				//en Many, on ne peut pas enlever des objets déjà assignés en base
////				if (disable === true) $scope['dsb' + $scope.values[i].id] = true;
////			}
////		}
////	};
////	$scope.init(disablePreChecked);
////	
////	$scope.updateValues = function(type, id, value) {
////		if (value) $scope.values.push({type: type, id: id});
////		else {
////			var index = null;
////			for(var i = 0; i < $scope.values.length; i++) {
////				if ($scope.values[i].id == id && $scope.values[i].type == type){
////					index = i;
////					break;
////				}
////			}
////			if (index != null) $scope.values.splice(index, 1);
////		}
////	};
////	
////	
////	$scope.overrideValues = function(type, id) {
////		$scope.values = [{type: type, id: id}];
////	};
//	
//	
//	
//	
//	
//	
//	
////	$scope.mkParams = function(pageable) {
////		var params = '';
////		var sep = '?';
////		if (pageable.size){
////			params += sep + 'size=' + pageable.size;
////			sep = '&';
////		}
////		if (pageable.sort){
////			params += sep + 'sort=' + pageable.sort;
////			sep = '&';
////		}
////		if (pageable.page){
////			params += sep + 'page=' + pageable.page;
////			sep = '&';
////		}
////		return params;
////	};
////	
////	$scope.mkUrl = function(urlInfos) {
////		var url = '/neutrino/bo/blocklist/' + urlInfos.type + '/' + urlInfos.field + '/' + urlInfos.id;
////		$scope.urlInfos = urlInfos;
////		$scope.urlMaked = url + $scope.mkParams(urlInfos.pageable);
////		$scope.init();
////	};
////	
////	$scope.updateUrlType = function(type) {
////		urlInfos.type = type;
////		$scope.mkUrl(urlInfos);
////	};
////	$scope.updateUrlPageableSize = function(size) {
////		urlInfos.pageable.size = size;
////		$scope.mkUrl(urlInfos);
////	};
////	$scope.updateUrlPageableSort = function(sort) {
////		urlInfos.pageable.sort = sort;
////		$scope.mkUrl(urlInfos);
////	};
////	$scope.updateUrlPageablePage = function(page) {
////		urlInfos.pageable.page = page;
////		$scope.mkUrl(urlInfos);
////	};
////	
////	$scope.mkUrl(urlInfos);
////
////	$scope.cancel = function() {
////		$uibModalInstance.dismiss('cancel');
////	};
////	
//	$scope.save = function(form) {
//		if (form.$valid) {
//			$uibModalInstance.close(values);
//		} else {
//			$uibModalInstance.dismiss('cancel');
//		}
//	};
//
//});