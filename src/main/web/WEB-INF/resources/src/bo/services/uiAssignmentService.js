(function() {

	var fModule = angular.module("frontApp");

	fModule.service('UiObjectAssignmentService', function($q, $log, $uibModal, $frontPath) {
		
		self = this;
		self.getObjects = function(values, objectType, objectId, objectField, kind, disablePreChecked, modalSize, pageSize, page) {
			if (!values) $log.error('values is mandatory !');
			if (!objectType) $log.error('objectType is mandatory !');
			if (!objectField) $log.error('objectField is mandatory !');
			if (!kind) kind = 'object';	
			if (!modalSize) modalSize = 'lg';
			if (!pageSize) pageSize = 5;
			if (!page) page = 0;
			
			var deferred = $q.defer();

			var instance = $uibModal.open({
				templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-object-assignement-modal.html',
				controller: 'UiObjectAssignmentModalCtrl',
				size: modalSize,
				resolve: {
		            values: function(){
		            	return values;
		            },
					urlInfos: function () {
		                return {
		                	type: objectType, 
		                	field: objectField, 
		                	id: objectId, 
							pageable: {size: pageSize, page: page,},
						}
		            },
		            disablePreChecked: function(){
		                return disablePreChecked;
		            },
		            kind: function(){
		            	return kind;
		            },
				}
			});
			instance.result.then(function(idProviders) {
				deferred.resolve(idProviders);
				console.log('idProviders = ');
				console.log(idProviders);
			}, function () {
				
		        console.log('Modal dismissed at: ' + new Date());
		    });
			return deferred.promise;
			
			
			
			
			
			

			
		};
		

	});
	
	
	fModule.controller('UiObjectAssignmentModalCtrl', function ($rootScope, $scope, $uibModalInstance, values, urlInfos, kind, disablePreChecked) {
		console.log('in UiObjectAssignmentModalCtrl');
		
		if (kind == 'object'){
			
			$scope.init = function(disable) {
				$scope.values = [];
				angular.extend($scope.values, values);
				for(var i = 0; i < $scope.values.length; i++) {
					$scope.lastCheckedId = $scope.values[i].id;
					$scope['chk' + $scope.values[i].id] = true;
					//en Many, on ne peut pas enlever des objets déjà assignés en base
					if (disable === true) $scope['dsb' + $scope.values[i].id] = true;
				}
			};

			$scope.init(disablePreChecked);
			
			$scope.updateValues = function(type, id, value) {
				if (value) $scope.values.push({type: type, id: id});
				else {
					var index = null;
					for(var i = 0; i < $scope.values.length; i++) {
						if ($scope.values[i].id == id && $scope.values[i].type == type){
							index = i;
							break;
						}
					}
					if (index != null) $scope.values.splice(index, 1);
				}
				console.log($scope.values);
			};
			$scope.overrideValues = function(type, id) {
				$scope.values = [{type: type, id: id}];
			};

			$scope.mkParams = function(pageable) {
				var params = '';
				var sep = '?';
				if (pageable.size){
					params += sep + 'size=' + pageable.size;
					sep = '&';
				}
				if (pageable.sort){
					params += sep + 'sort=' + pageable.sort;
					sep = '&';
				}
				if (pageable.page){
					params += sep + 'page=' + pageable.page;
					sep = '&';
				}
				return params;
			};
			
			$scope.mkUrl = function(urlInfos) {
				var id = urlInfos.id == '' ? 0 : urlInfos.id;
				var url = '/neutrino/bo/blocklist/' + urlInfos.type + '/' + id + '/' + urlInfos.field;
				$scope.urlInfos = urlInfos;
				$scope.urlMaked = url + $scope.mkParams(urlInfos.pageable);
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

		} else if (kind == 'objectFile'){
			$scope.mkUrl = function(urlInfos) {
				var url = '/neutrino/bo/file/'; //TODO bonne url
				$scope.urlMaked = url;
			}
			$scope.mkUrl();

			$rootScope.$on('filemanagerSelectItemChanged', function(event, data) {
				$scope.values = [];
				for(var i = 0; i < data.length; i++) {
					var obj = data[i].model.tag;
					$scope.values.push({type: obj.objectType, id: obj.id});
					if ($scope.templateForm) $scope.templateForm.$dirty = true;
				}
			});
		}

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
	

	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}());