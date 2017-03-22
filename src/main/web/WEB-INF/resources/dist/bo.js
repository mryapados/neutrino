(function() {

	var fModule = angular.module('frontApp', ['frontServices', 'ui.bootstrap', 'textAngular', 'ngSanitize', 'pascalprecht.translate', 'ngCookies', 'ngResource']);
	fModule.config(function($translateProvider) {
		var $cookies;
		angular.injector(['ngCookies']).invoke(['$cookies', function(_$cookies_) {
			$cookies = _$cookies_;
		}]);
		
		$translateProvider.useSanitizeValueStrategy('sanitize');
		var lang = $cookies.get('language');
		if (lang == undefined){lang = 'en';}
		$translateProvider.preferredLanguage(lang);
		$translateProvider.useLoader('i18nLoader');
		
	});
	fModule.run(function($cookies) {

		
	});
	fModule.factory('i18nLoader', function ($http, $q, $frontPath) {
	    return function (options) {
		    var deferred = $q.defer();
		    $http.get($frontPath.URL_I18N + options.key + '.json')
			.then(function(response) {
	            deferred.resolve(response.data);
			})
			.catch(function(error){
				console.log(error.status);
				console.log(error);
				deferred.reject(options.key);
			});
			return deferred.promise;
	    };
	});
	
	
	
	


	
	
	
	
}());
var fModule = angular.module('frontApp');
fModule.controller('DatepickerPopupCtrl', function ($scope) {
	
  $scope.init = function(dateObj) {
	$scope.dt = new Date(dateObj);
  }	
	
  $scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function() {
    $scope.dt = null;
  };

  $scope.inlineOptions = {
    customClass: getDayClass,
    minDate: new Date(),
    showWeeks: true
  };

  $scope.dateOptions = {
    dateDisabled: disabled,
    formatYear: 'yy',
    maxDate: new Date(2020, 5, 22),
    minDate: new Date(),
    startingDay: 1
  };

  // Disable weekend selection
  function disabled(data) {
    var date = data.date,
      mode = data.mode;
    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
  }

  $scope.toggleMin = function() {
    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
  };

  $scope.toggleMin();

  $scope.open1 = function() {
    $scope.popup1.opened = true;
  };

  $scope.open2 = function() {
    $scope.popup2.opened = true;
  };

  $scope.setDate = function(year, month, day) {
    $scope.dt = new Date(year, month, day);
  };

  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
  $scope.format = $scope.formats[0];
  $scope.altInputFormats = ['M!/d!/yyyy'];

  $scope.popup1 = {
    opened: false
  };

  $scope.popup2 = {
    opened: false
  };

  var tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);
  var afterTomorrow = new Date();
  afterTomorrow.setDate(tomorrow.getDate() + 1);
  $scope.events = [
    {
      date: tomorrow,
      status: 'full'
    },
    {
      date: afterTomorrow,
      status: 'partially'
    }
  ];

  function getDayClass(data) {
    var date = data.date,
      mode = data.mode;
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i = 0; i < $scope.events.length; i++) {
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  }
});

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
var fModule = angular.module('frontApp');
fModule.controller('WysiwygEditorCtrl', function ($scope, textAngularManager) {
    $scope.version = textAngularManager.getVersion();
    $scope.versionNumber = $scope.version.substring(1);
    $scope.disabled = false;
});

(function() {

	var fModule = angular.module("frontApp");

	fModule.directive('uiAssignment', function($parse, $frontPath){
		return {
			restrict: 'E',
			require : '?ngModel',
			transclude: true,
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement.html', 
			controller: function ( $scope, UiAssignmentService ) {
				$scope.open = function(size) {
					UiAssignmentService.getObjects($scope[$scope.model], $scope.type, $scope.id, $scope.field, $scope.disablePreChecked, size).then(function(objects) {
						$parse($scope.model).assign($scope.$parent, objects);
					})
					.catch(function(error){
						console.log(error.status);
						console.log(error);
					});
				};
			}, 
			scope : true,
			link: function(scope, element, attrs, ngModelController){
				scope.type = attrs['type'];
				scope.field = attrs['field'];
				scope.id = attrs['id'];
				scope.model = attrs['ngModel'];
				scope.disablePreChecked = (attrs['disablePreChecked'] == 'true');
	        },
		};
	});

	fModule.directive('input', function($parse, idProvidersFilter, $compile, $http, $frontPath) {
		return {
			restrict : 'E',
			require : '?ngModel',
		    controller: function ( $scope, $element, $attrs ) {
		        if ($attrs.assign){
		        	var disablePreChecked = false;
		        	if ($attrs.disablePreChecked){
		        		disablePreChecked = $attrs.disablePreChecked;
		        	}

		        	var res = $attrs.assign.split('_');
		        	var type = res[0];
		        	var id = res[1];
		        	var field = res[2];
		        	var model = $attrs.ngModel;
			        var uiAssign = $compile('<data-ui-assignment type="' + type + '" field="' + field + '" id="' + id + '" ng-model="' + model + '" disable-pre-checked="' + disablePreChecked + '"/></data-ui-assignment>')( $scope );
			        $element.parent().append(uiAssign);

			        var modelText = $attrs.ngModel + 'Text';
			        var uiAssignText = $compile('<ul class="linked"><li data-ng-repeat="r in ' + modelText + ' | orderBy: \'name\'"><a class="linked" href="#">{{r.name}}</a></li><li data-ng-show="' + model+ '.length - ' + modelText + '.length > 0"><strong><a href="#">{{' + model+ '.length - ' + modelText + '.length}} <span>Others results...</span></a></strong></li></ul>')( $scope );
			        $element.parent().append(uiAssignText);

			        $scope.$watch(model, function() {			        	
			        	var max = 5;
						var objects = $scope[model];
			      		if (objects && objects.length > 0){
			      			if (!max) max = objects.length;
			      			else if (objects.length < max) max = objects.length;
			      			
			      			//Le type de la collection est identifié par le 1er de la liste
			      			var type = objects[0].type;
			      			
			      			//Chaque id est récupéré
			      			var ids = [];
			      			for(var i = 0; i < max; i++) {
			      				if (objects[i].id){
			      					ids.push(objects[i].id);
			      				}
			      			}
			      			console.log(ids);
			      			//Récupère la liste des objets avec leur nom via une requête au serveur
			      			if (ids.length > 0){
					      		$http.get($frontPath.URL_SERVER_REST + 'bo/objects/' + type, {params:{'id': ids}}).then(function(data) {
					      			$scope[modelText] = data.data;
					      			
								})
			      			}

			      		}

			        });

		        }
		    },
			link : function(scope, element, attrs, ngModelController) {
				if (attrs.ngModel && attrs.assign) {
					$parse(attrs.ngModel).assign(scope, idProvidersFilter(attrs.value, 'toArray'));
					ngModelController.$parsers.push(function(data) {
						// convert data from view format to model format
						return idProvidersFilter(data, 'toArray'); // converted
					});
					ngModelController.$formatters.push(function(data) {
						// convert data from model format to view format
						return idProvidersFilter(data);  // converted
					});
					
				}
			}
		}
	});
	


	
}());
(function() {

	var fModule = angular.module("frontApp");



	
}());
(function() {
	
	var fModule = angular.module("frontApp");

	fModule.filter('idProviders', function () {
		return function(object, method) {
			if (!method) method = 'toString';
			if (method == 'toArray'){
		    	var res = object.split(",");
		    	var idproviders = [];
		    	angular.forEach(res, function(value) {
		    		if (value != ''){
			    		var idprovider = value.split("_");
			    		idproviders.push({type:idprovider[0], id:Number(idprovider[1])});  
		    		}
		    	});
		    	return idproviders;
			} else {
				var res = '';
		    	angular.forEach(object, function(value) {
		    		res += value.type + '_' + value.id + ',';
		    	});
		    	return res;
			}
		};
	});
	
	
	

	

	
	
	
	
	
	
	
//	fModule.filter('idProviderInList', function ($filter) {
//		return function(list, type, id) {
//			for(var i = 0; i < list.length; i++) {
//			    if (list[i].id == id && list[i].type == type) {
//			    	return true;
//			    }
//			}
//			return false;
//		};
//	});

}());
(function() {
	var bModule=angular.module("frontServices",['FileManagerApp']);

	angular.module('FileManagerApp').config(['fileManagerConfigProvider', function (config) {
      var defaults = config.$get();
      config.set({
        appName: 'angular-filemanager',
        pickCallback: function(item) {
          var msg = 'Picked %s "%s" for external use'
            .replace('%s', item.type)
            .replace('%s', item.fullPath());
          window.alert(msg);
        },

        allowedActions: angular.extend(defaults.allowedActions, {
          pickFiles: false,
          pickFolders: false,
        }),
      });
    }]);
	
	
}());
(function() {
	var fModule = angular.module('frontServices');
	fModule.constant('$frontPath', {
		URL_SERVER_REST: '/neutrino/',
		URL_TEMPLATE_JS: '/neutrino/resources/src/bo/templates/',
		URL_I18N: '/neutrino/resources/src/bo/i18n/',
	});

}());
(function() {

	var fModule = angular.module("frontApp");

	fModule.service('UiAssignmentService', function($q, $log, $uibModal, $frontPath) {
		
		self = this;
		self.getObjects = function(values, objectType, objectId, objectField, disablePreChecked, modalSize, pageSize, page) {
			if (!values) $log.error('values is mandatory !');
			if (!objectType) $log.error('objectType is mandatory !');
			//if (!objectId) $log.error('objectId is mandatory !');
			if (!objectField) $log.error('objectField is mandatory !');
			if (!modalSize) modalSize = 'lg';
			if (!pageSize) pageSize = 5;
			if (!page) page = 0;
			
			var deferred = $q.defer();

			var instance = $uibModal.open({
				templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement-modal.html',
				controller: 'UiAssignmentModalCtrl',
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
	
	fModule.controller('UiAssignmentModalCtrl', function ($scope, $uibModalInstance, values, urlInfos, disablePreChecked) {
		console.log('in UiAssignmentModalCtrl');

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
	
		
		
		
		
		
		
		
		

	
	});
		
		
		
		
		
		
	
	
	
	
}());