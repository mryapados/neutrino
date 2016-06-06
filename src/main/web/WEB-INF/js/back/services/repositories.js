(function() {
	var module = angular.module('backServices');

//	module.factory("BlockRepository", function($http, PATH) {
//		return {
//			getAllForModel: function(modelName) {
//				return $http.get(PATH.URL_SERVER_REST + '@back/blocks/' + modelName)
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
	module.factory("TemplateRepository", function($http, PATH) {
		return {
			exist: function(context, type, path, name) {
				return $http.get(
					PATH.URL_SERVER_REST + '@back/templates/exist/', 
					{params:{'context': context, 'type': type, 'path': path, 'name': name}}
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
	
	module.factory("TemplateResource", function($resource, PATH) {
	  	var API_URI = '@back/templates/:name';  				
	  	return $resource(PATH.URL_SERVER_REST + API_URI, {name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	module.factory("PageResource", function($resource, PATH) {
	  	var API_URI = '@back/pages/:name';  				
	  	return $resource(PATH.URL_SERVER_REST + API_URI, {name: '@name'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	module.factory("BlockResource", function($resource, PATH) {
	  	var API_URI = '@back/models/:model/positions/:position/blocks';  				
	  	return $resource(PATH.URL_SERVER_REST + API_URI, {model: '@model', positions: '@positions'}, {
	  		create: {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	module.factory("PositionRepository", function($http, PATH) {
		return {
			get: function(positionName) {
				return $http.get(PATH.URL_SERVER_REST + '@back/positions/'+ positionName)
				.then(function(response) {
					return response.data;
				})
				.catch(function(error){
					console.log(error.status);
					console.log(error);
				});
			},
			getAll: function() {
				return $http.get(PATH.URL_SERVER_REST + '@back/positions/')
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

	module.factory("MapTemplateRepository", function($http, PATH) {
		return {
			remove: function(idTemplateBlock) {
				return $http.delete(PATH.URL_SERVER_REST + '@back/removemapblock/' + idTemplateBlock)
				.then(function(response) {

				})
				.catch(function(error){
					console.log(error.status);
					console.log(error);
				});
			},
			add: function(dataObj) {
	    		return $http.post(PATH.URL_SERVER_REST + '@back/addmapblock', dataObj).then(function(response) {
	    			return response.data;
				}).catch(function(error){
					console.log(error.status);
					console.log(error);
				});

			}
		};
	});
	
	
	module.factory("MapTemplateResource", function($resource, PATH) {
	  	var API_URI = '@back/maptemplates/:id';			
	  	return $resource(PATH.URL_SERVER_REST + API_URI, {id: '@id'}, {
	  		save:   {method: 'POST'},
	  		get:    {method: 'GET'},
	  		getAll: {method: 'GET', isArray: true},
	  		remove: {method: 'DELETE'},
	  		update: {method: 'PUT'}
	  	});
	});
	
	
	
	
}());