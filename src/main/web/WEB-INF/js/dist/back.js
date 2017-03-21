(function() {
	var bModule = angular.module('backApp', [ 'backServices', 'ngSanitize', 'pascalprecht.translate', 'ngCookies', 'ngResource', 'frontApp' ]);
	bModule.config(function($translateProvider) {
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
	bModule.run(function($cookies) {

		
	});
	bModule.factory('i18nLoader', function ($http, $q, $backPath) {
	    return function (options) {
		    var deferred = $q.defer();
			$http.get($backPath.URL_SERVER_REST + '@front/labels/' + options.key)
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
var bModule = angular.module("backApp");

bModule.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, $backPath) {
	$scope.init = function(folderName, pageName, activeObjectId) {
		BlockManagementService.init(folderName, pageName, activeObjectId).then(function() {
			$scope.BlockManagementFacade = BlockManagementService;
		});
	}
});
var bModule = angular.module("backApp");

bModule.controller('BlockSelectionCtrl', function($scope) {
	
	$scope.currentLang = '';
	$scope.switchLang = function(codeLang) {
		$scope.currentLang = codeLang;
	};

	$scope.openValue = false;
	$scope.switchDisplay = function() {
		$scope.openValue = !$scope.openValue;
	};

});




var bModule = angular.module("backApp");

bModule.controller('ModalDemoCtrl', function ($scope, $uibModal, $log) {

  $scope.items = ['item1', 'item2', 'item3'];

  $scope.open = function (size) {
    var modalInstance;
    var modalScope = $scope.$new();
    modalScope.ok = function () {
            modalInstance.close(modalScope.selected);
    };
    modalScope.cancel = function () {
            modalInstance.dismiss('cancel');
    };      
    
    modalInstance = $uibModal.open({
      template: '<my-modal></my-modal>',
      size: size,
      scope: modalScope
      }
    );

    modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };
});

var bModule = angular.module("backApp");

bModule.controller('TemplateModalCtrl', function($scope, $uibModalInstance, $backPath, template, page, TemplateService) {	
	$scope.exist = function() {
		return TemplateService.getExist(page.context, template.kind, template.path, template.name)
		.then(function(data) {
			$scope.scriptExist = {
				exist: data, 
				path: '\"...' + template.path + '/' + template.name + '.jsp\"',
			};
		});
	};
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	$scope.updateTemplate = function(form) {
		if (form.$valid) {
			TemplateService.saveTemplate(template).then(function(data) {
				$uibModalInstance.close(data);
			}).catch(function(error) {
				$scope.template.errors = error;
			}).finally(function() {
				
			});
		} else {
			$uibModalInstance.dismiss('cancel');
		}

	};
	
	$scope.template = {
		templateModal: $backPath.URL_TEMPLATE_MODAL, 
		template: template,
		errors: null, 
	};
	
	$scope.exist();
});




var bModule = angular.module('backApp');

bModule.controller('UiPositionCtrl', function($scope, BlockService, MapTemplateService, TemplateService, BlockManagementService) {
	TemplateService.getTemplate($scope.model).then(function(data) {
		var model = data;
		var position = $scope.position;
		var activeObject = $scope.activeobject;

		$scope.modelKind = model.kind;	
		
		BlockService.getBlocksForModelPosition(model.name, activeObject, position).then(function(data) {
			$scope.blocks = data;
		});
		
	    $scope.$on('dropEvent_' + position + '@' + model.name, function (evt, dragged, dropped) {
	        //console.log('Block : ', dragged, 'Position : ', dropped, 'Model : ', model, 'Ordered : ', dropped.ordered);
	        BlockManagementService.setMapBlock(model.name, dragged.name, dropped.name, dropped.ordered).then(function(data){
	        	if (dropped.ordered < 0){
	        		$scope.blocks.splice(0, 0, data);
	        	} else {
	        		$scope.blocks.push(data);
	        	}
			});
	    }); 
		
	    $scope.remove = function(blockIndex) {
			MapTemplateService.remove($scope.blocks[blockIndex].idMapTemplate).then(function(data) {
				$scope.blocks.splice(blockIndex, 1);
			});
		}
		
	});
	
	


});
(function() {
	
	var bModule = angular.module("backApp");
	bModule.directive("drag", ["$rootScope", function ($rootScope) {

        function dragStart(evt, element, dragStyle) {
            element.addClass(dragStyle);
            evt.originalEvent.dataTransfer.setData( "id", evt.target.id );
            evt.originalEvent.dataTransfer.effectAllowed = 'move';
        };

        function dragEnd(evt, element, dragStyle) {
            element.removeClass(dragStyle);
        };

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                attrs.$set('draggable', 'true');
                scope.dragStyle = attrs["dragstyle"];
                element.bind('dragstart', function (evt) {
                    $rootScope.draggedElement = scope[attrs["drag"]];
                    dragStart(evt, element, scope.dragStyle);
                });
                element.bind('dragend', function (evt) {
                    dragEnd(evt, element, scope.dragStyle);
                });
            }
        }
    }]);

	bModule.directive("drop", ['$rootScope', function ($rootScope) {

        function dragEnter(evt, element, dropStyle) {
            evt.preventDefault();
            element.addClass(dropStyle);
        };

        function dragLeave(evt, element, dropStyle) {
            element.removeClass(dropStyle);
        };

        function dragOver(evt) {
            evt.preventDefault();
        };

        function drop(evt, element, dropStyle) {
            evt.preventDefault();
            element.removeClass(dropStyle);
        };

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                scope.dropStyle = attrs["dropstyle"];
                element.bind('dragenter', function (evt) {
                    dragEnter(evt, element, scope.dropStyle);
                });
                element.bind('dragleave', function (evt) {
                    dragLeave(evt, element, scope.dropStyle);
                });
                element.bind('dragover', dragOver);
                element.bind('drop', function (evt) {
                    drop(evt, element, scope.dropStyle);
                    var model = scope.model;
                    if (!scope[attrs['drop']]) {
                        var res = attrs['drop'].split('-');
                        var ordered = 0;
                        if (res[1] == 'top'){
                        	ordered = -1;
                        } else {
                        	ordered = 1;
                        }
                        dropData = {name: res[0], ordered:ordered};
                    } else {
                    	dropData = scope[attrs["drop"]];
                    }
                    var pathEvent = 'dropEvent_' + dropData.name + '@' + model;
                    $rootScope.$broadcast(pathEvent, $rootScope.draggedElement, dropData);

                });
            }
        }
    }]);
	
	

	bModule.directive('uiPosition', function($backPath, BlockManagementService){
		return {
			scope: {
				position: '@',
				model: '@',
				activeobject: '@',
			},
			restrict: 'E',
			templateUrl: $backPath.URL_TEMPLATE_JS + 'ui-position.html', 
			controller: 'UiPositionCtrl', 
			link: function(scope, element, attrs){
				scope.facade = BlockManagementService;
	        },
		};
	});
	

	
	bModule.directive('myModal', function() {
	    return {
	        restrict: 'E',
	        templateUrl: 'myModalContent.html',
	        controller: function ($scope) {
	          $scope.selected = {
	            item: $scope.items[0] 
	          };
	        }
	    };
	});

	
	
	
}());
(function() {
	
//	var module = angular.module("backApp");
//	module.filter('getById', function($filter) {
//		return function(list, id, idAttr) {
//			var filterAttr = 'id';
//			if (idAttr && angular.isString(idAttr)) {
//				filterAttr = idAttr;
//			}
//			var filterObject = {};
//			filterObject[filterAttr] = id;
//			var filtersData = $filter('filter')(list, filterObject, true);
//			if (filtersData) {
//				return filtersData[0];
//			} else {
//				return null;
//			}
//		};
//	});
	
	
	
	
}());


(function() {
	var bModule=angular.module("backServices",[]);
	bModule.config(function($httpProvider) {
		$httpProvider.interceptors.push('Interceptor');
	});
	
	bModule.factory('Interceptor', function($q) {
		return {
			responseError: function(rejection) {
				if (rejection.status === 404) {
					rejection.data = '<div class="bg-danger"><h2>Object not found</h2><p>' + rejection.config.url + '</p></div>'
					return $q.resolve(rejection);
				}
				return $q.reject(rejection);
			}
		};
	});
}());
(function() {
	var bModule = angular.module('backServices');

	bModule.service('BlockManagementService', function($rootScope, $q, $uibModal, FolderService, LangService, PageService, TemplateService, BlockService, MapTemplateService, TObjectService, $backPath) {
		var folder;
		var page;
		var activeObject = null;
		self = this;
		
		var templates;
		self.getTemplates = function() {
			return templates;
		};
		var langs;
		self.getLangs = function() {
			return langs;
		};
		
		self.init = function(folderName, pageName, activeObjectId) {
			var deferred = $q.defer();
			
			var promise = FolderService.getFolder(folderName).then(function(data){
				folder = data;
			})
			.then(function(){
				self.initPage(pageName);
			})
			.then(function(){
				if (activeObjectId != null){
					TObjectService.getTObject(activeObjectId).then(function(data){
						activeObject = data;
					});
				}
			})
			.then(function(){
				self.initLangs();
			})
			.then(function(){
				self.initTemplates();
			})
			.catch(function(error) {
				console.log('errrrrrrrrrrrrrrrrrrrrror');
				console.log(error);
			})
			.finally(function() {
				deferred.resolve();
			});
			
			
			
//			var promise = PageService.getPage(pageName)
//			.then(function(data){
//				page = data;
//			})
//			.then(function(){
//				if (activeObjectId != null){
//					TObjectService.getTObject(activeObjectId).then(function(data){
//						activeObject = data;
//					});
//				}
//			})
//			.then(function(){
//				self.initLangs();
//				
//				
//			})
//			.then(function(){
//				self.initTemplates();
//			})
//			.catch(function(error) {
//				console.log(error);
//			})
//			.finally(function() {
//				deferred.resolve();
//			});
			return deferred.promise;
			
			
		};
		
		self.initFolder = function(folderName) {
			var deferred = $q.defer();
			if (!folder) {
				FolderService.getFolder(folderName).then(function(data){
					folder = data;
					deferred.resolve();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};
		
		self.initPage = function(pageName) {
			var deferred = $q.defer();
			if (!page) {
				PageService.getPage(pageName).then(function(data){
					page = data;
					deferred.resolve();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};
		
		self.initLangs = function() {
			var deferred = $q.defer();
			if (!langs) {
				LangService.getLangs().then(function(data) {
					langs = data;
					deferred.resolve();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};
		
//		self.initTemplates = function() {
//			var deferred = $q.defer();
//			if (!templates) {
//				TemplateService.getTemplates().then(function(data) {
//					templates = data;
//					console.log(data);
//					deferred.resolve();
//				});
//			} else {
//				deferred.resolve();
//			}
//			return deferred.promise;
//		};
		
		self.initTemplates = function() {
			var deferred = $q.defer();
			if (!templates) {
				TemplateService.getTemplates().then(function(data) {
					templates = data;
					deferred.resolve();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};
		self.getParsedBlock = function(blockName) {
			return $backPath.URL_SERVER_REST + '@back/parsedblock/' + page.name + '/' + blockName + '?servername=' + folder.serverName[0];
		}

		self.setMapBlock = function(modelName, blockName, positionName, ordered) {
    		var dataObj = {
    				modelName : modelName,
    				blockName : blockName,
    				positionName : positionName, 
    				ordered : ordered
    		};
			var deferred = $q.defer();
			MapTemplateService.save(dataObj).then(function(data) {
				deferred.resolve(data);
			});
			return deferred.promise;
		}
		
        self.getPathTemplateForm = function() {
        	return $backPath.URL_TEMPLATE_MODAL;
        }
        
        self.openModal = function(templateId) {
			var instance = $uibModal.open({
				templateUrl: $backPath.URL_TEMPLATE_MODAL_EDIT,
				controller: 'TemplateModalCtrl',
				resolve: {
					template: function(){
						return TemplateService.getTemplate(templateId);
					}, 
					page: function() { return page;}, 
				}
			});
			instance.result.then(function(savedTemplate) {
				
				if (templates) {
					// TODO Update templates list
					
					//var oldTemplate =getByIdFilter(templates, templateId);
					//var index = templates.indexOf(oldTemplate);

					//templates[index] = savedTemplate;
	
				
	
				}

				
				
				
			});
		};

	});
	

	
	bModule.service('TemplateService', function(TemplateResource, TemplateRepository, $backPath) {
		self = this;
		self.getTemplate = function(templateName) {
			return TemplateResource.get({name : templateName}).$promise;
		};
		self.getTemplates = function() {
			return TemplateResource.getAll().$promise;
		};
		self.getExist = function(context, kind, path, name) {
			return TemplateRepository.exist(context, kind, path, name);
		};
		self.saveTemplate = function(template) {


			var fn = (template.id) ? TemplateResource.update : TemplateResource.create;
			return fn.call(TemplateResource, {}, template).$promise;
//			.then(function(savedTemplate) {
//				//if (movies) {
//					if (!template.id) {
//						//movies.push(savedTemplate);
//					} else {
//						//var oldMovie =getByIdFilter(movies, movie.id);
//						//var index = movies.indexOf(oldMovie);
//
//						//movies[index] = savedTemplate;
//
//					}
//
//				//}
//				return savedTemplate;
//			});

			//return fn.call(TemplateResource, {}, template).$promise;

		};
	});
	bModule.service('LangService', function(LangResource, $backPath) {
		self = this;
		self.getLang = function(langName) {
			return LangResource.get({name : langName}).$promise;
		};
		self.getLangs = function() {
			return LangResource.getAll().$promise;
		};
	});
	bModule.service('FolderService', function(FolderResource, $backPath) {
		self = this;
		self.getFolder = function(folderName) {
			return FolderResource.get({name : folderName}).$promise;
		};
		self.getFolders = function() {
			return FolderResource.getAll().$promise;
		};
	});
	bModule.service('PageService', function(PageResource, $backPath) {
		self = this;
		self.getPage = function(pageName) {
			return PageResource.get({name : pageName}).$promise;
		};
		self.getPages = function() {
			return PageResource.getAll().$promise;
		};
	});
	bModule.service('TObjectService', function(TObjectResource, $backPath) {
		self = this;
		self.getTObject = function(id) {
			return TObjectResource.get({id : id}).$promise;
		};
	});
	
	
	
	bModule.service('BlockService', function(BlockResource, $backPath) {
		self = this;
		self.getBlocksForModelPosition = function(modelName, activeObject, positionName) {
			return BlockResource.getAll({model : modelName, activeobject:activeObject, position : positionName}).$promise;
		};
	});
	bModule.service('PositionService', function(PositionRepository, $backPath) {
		self = this;
		self.getPosition = function(positionName) {
			return PositionRepository.get(positionName);
		};
		self.getPositions = function() {
			return PositionRepository.getAll();
		};		
	});
	bModule.service('MapTemplateService', function(MapTemplateResource, $backPath) {
		self = this;
		self.save = function(mapTemplate) {
			var fn = (mapTemplate.id) ? MapTemplateResource.update : MapTemplateResource.save;
			return fn.call(MapTemplateResource, {}, mapTemplate).$promise;
		};
		self.remove = function(id) {
			return MapTemplateResource.remove({id:id}).$promise;
		};
	});
}());
(function() {
	var bModule = angular.module('backServices');
	bModule.constant('$backPath', {
		URL_SERVER_REST: '/neutrino/',
		URL_TEMPLATE_JS: '/neutrino/js/src/back/templates/',
		URL_TEMPLATE_MODAL: '/neutrino/js/src/back/views/templateForm.html',
		URL_TEMPLATE_MODAL_ADD: '/neutrino/js/src/back/views/modalTemplateFormAdd.html',
		URL_TEMPLATE_MODAL_EDIT: '/neutrino/js/src/back/views/modalTemplateFormEdit.html',
	});

}());
(function() {
	var bModule = angular.module('backServices');

//	module.factory("BlockRepository", function($http, $backPath) {
//		return {
//			getAllForModel: function(modelName) {
//				return $http.get($backPath.URL_SERVER_REST + '@back/blocks/' + modelName)
//				.then(function(response) {
//		            return response.data;
//				})
//				.catch(function(error){
//					console.log(error.status);
//					console.log(error);
//				});
//			}
//		};
//	});
	bModule.factory("TemplateRepository", function($http, $backPath) {
		return {
			exist: function(context, kind, path, name) {
				return $http.get(
						$backPath.URL_SERVER_REST + '@back/templates/exist/', 
					{params:{'context': context, 'kind': kind, 'path': path, 'name': name}}
				).then(function(response) {
					return response.data;
				})
				.catch(function(error){
					console.log(error.status);
					console.log(error);
				});
			}, 
		};
	});
	
	bModule.factory("TemplateResource", function($resource, $backPath) {
	  	var API_URI = '@back/templates/:name';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("LangResource", function($resource, $backPath) {
	  	var API_URI = '@back/langs/:name';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	
	bModule.factory("FolderResource", function($resource, $backPath) {
	  	var API_URI = '@back/folders/:name';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("PageResource", function($resource, $backPath) {
	  	var API_URI = '@back/pages/:name';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("BlockResource", function($resource, $backPath) {
	  	var API_URI = '@back/models/:model/activeobjects/:activeobject/positions/:position/blocks';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {model: '@model', activeobject: '@activeobject', positions: '@position'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("PositionRepository", function($http, $backPath) {
		return {
			get: function(positionName) {
				return $http.get($backPath.URL_SERVER_REST + '@back/positions/'+ positionName)
				.then(function(response) {
					return response.data;
				})
				.catch(function(error){
					console.log(error.status);
					console.log(error);
				});
			},
			getAll: function() {
				return $http.get($backPath.URL_SERVER_REST + '@back/positions/')
				.then(function(response) {
		            return response.data;
				})
				.catch(function(error){
					console.log(error.status);
					console.log(error);
				});

			}

		};
	});

	bModule.factory("MapTemplateRepository", function($http, $backPath) {
		return {
			remove: function(idTemplateBlock) {
				return $http.delete($backPath.URL_SERVER_REST + '@back/removemapblock/' + idTemplateBlock)
				.then(function(response) {

				})
				.catch(function(error){
					console.log(error.status);
					console.log(error);
				});
			},
			add: function(dataObj) {
	    		return $http.post($backPath.URL_SERVER_REST + '@back/addmapblock', dataObj).then(function(response) {
	    			return response.data;
				}).catch(function(error){
					console.log(error.status);
					console.log(error);
				});

			}
		};
	});
	
	
	bModule.factory("MapTemplateResource", function($resource, $backPath) {
	  	var API_URI = '@back/maptemplates/:id';			
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		save:   {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("TObjectResource", function($resource, $backPath) {
	  	var API_URI = '@back/tobjects/:id';			
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		save:   {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	
}());