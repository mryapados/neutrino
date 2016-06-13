(function() {
	var module = angular.module('backServices');

	module.service('BlockManagementService', function($rootScope, $q, $modal, LangService, PageService, TemplateService, BlockService, MapTemplateService, PATH) {
		var page;
		self = this;
		
		var templates;
		self.getTemplates = function() {
			return templates;
		};
		var langs;
		self.getLangs = function() {
			return langs;
		};
		
		self.init = function(viewPage) {
			var deferred = $q.defer();
			var promise = PageService.getPage(viewPage)
			.then(function(data){
				page = data;
			})
			.then(function(){
				self.initLangs();
			})
			.then(function(){
				self.initTemplates();
			})
			.catch(function(error) {
				console.log(error);
			})
			.finally(function() {
				deferred.resolve();
			});
			return deferred.promise;
		};
		
		
		self.initLangs = function() {
			var deferred = $q.defer();
			if (!langs) {
				LangService.getLangs().then(function(data) {
					console.log(data);
					langs = data;
					deferred.resolve();
				});
			} else {
				deferred.resolve();
			}
			return deferred.promise;
		};
		
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
			return PATH.URL_SERVER_REST + '@back/parsedblock/' + page.name + '/' + blockName;
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
        	return PATH.URL_TEMPLATE_MODAL;
        }
        
        self.openModal = function(templateId) {
			var instance = $modal.open({
				templateUrl: PATH.URL_TEMPLATE_MODAL_EDIT,
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
	

	
	module.service('TemplateService', function(TemplateResource, TemplateRepository, PATH) {
		self = this;
		self.getTemplate = function(templateName) {
			return TemplateResource.get({name : templateName}).$promise;
		};
		self.getTemplates = function() {
			return TemplateResource.getAll().$promise;
		};
		self.getExist = function(context, type, path, name) {
			return TemplateRepository.exist(context, type, path, name);
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
	module.service('LangService', function(LangResource, PATH) {
		self = this;
		self.getLang = function(langName) {
			return LangResource.get({name : langName}).$promise;
		};
		self.getLangs = function() {
			return LangResource.getAll().$promise;
		};
	});
	module.service('PageService', function(PageResource, PATH) {
		self = this;
		self.getPage = function(pageName) {
			return PageResource.get({name : pageName}).$promise;
		};
		self.getPages = function() {
			return PageResource.getAll().$promise;
		};
	});
	module.service('BlockService', function(BlockResource, PATH) {
		self = this;
		self.getBlocksForModelPosition = function(modelName, positionName) {
			return BlockResource.getAll({model : modelName, position : positionName}).$promise;
		};
	});
	module.service('PositionService', function(PositionRepository, PATH) {
		self = this;
		self.getPosition = function(positionName) {
			return PositionRepository.get(positionName);
		};
		self.getPositions = function() {
			return PositionRepository.getAll();
		};		
	});
	module.service('MapTemplateService', function(MapTemplateResource, PATH) {
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