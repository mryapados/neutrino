(function() {
	var fModule = angular.module('frontServices');
	fModule.constant('$frontPath', {
		URL_SERVER_REST: '/neutrino/',
		URL_TEMPLATE_JS: '/neutrino/resources/src/bo/templates/',
		URL_I18N: '/neutrino/resources/src/bo/i18n/',
	});

}());