(function() {
	var fModule = angular.module('frontServices');
	fModule.constant('$frontPath', {
		URL_SERVER_REST: '/neutrino/',
		URL_TEMPLATE_JS: '/neutrino/js/bo/templates/',
		URL_I18N: '/neutrino/js/bo/i18n/',
	});

}());