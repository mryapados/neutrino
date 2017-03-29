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
		
		console.log(lang);
	});
	bModule.run(function($cookies) {

		
	});
	bModule.factory('i18nLoader', function ($http, $q, $backPath) {
		console.log('eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee');
	    return function (options) {
	    	console.log('ffffffff');
	    	
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