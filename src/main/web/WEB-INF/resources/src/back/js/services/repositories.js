(function() {
	var bModule = angular.module('backServices');

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
	  	var API_URI = '@back/template';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("LangResource", function($resource, $backPath) {
	  	var API_URI = '@back/lang';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id', code: '@code'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	
	bModule.factory("FolderResource", function($resource, $backPath) {
	  	var API_URI = '@back/folder';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id', name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("PageResource", function($resource, $backPath) {
	  	var API_URI = '@back/page';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("BlockResource", function($resource, $backPath) {
	  	var API_URI = '@back/block';  				
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {modelId: '@modelId', pageId: '@pageId', activeObjectId: '@activeObjectId', positionId: '@positionId'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("MapTemplateResource", function($resource, $backPath) {
	  	var API_URI = '@back/mapTemplate';			
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		save:   {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	bModule.factory("TObjectResource", function($resource, $backPath) {
	  	var API_URI = '@back/tobject';			
	  	return $resource($backPath.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		save:   {method: 'POST'},
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
	
	

	

	
	
}());