(function() {
	var module = angular.module('backApp', [ 'backServices', 'ngSanitize', 'pascalprecht.translate', 'ngCookies', 'ui.bootstrap', 'ngResource' ]);
	module.config(function($translateProvider) {
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
	module.run(function($cookies) {

		
	});
	module.factory('i18nLoader', function ($http, $q, PATH) {
	    return function (options) {
		    var deferred = $q.defer();
			$http.get(PATH.URL_SERVER_REST + '@front/labels/' + options.key)
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