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