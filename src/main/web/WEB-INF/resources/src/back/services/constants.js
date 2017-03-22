(function() {
	var bModule = angular.module('backServices');
	bModule.constant('$backPath', {
		URL_SERVER_REST: '/neutrino/',
		URL_TEMPLATE_JS: '/neutrino/resources/src/back/templates/',
		URL_TEMPLATE_MODAL: '/neutrino/resources/src/back/views/templateForm.html',
		URL_TEMPLATE_MODAL_ADD: '/neutrino/resources/src/back/views/modalTemplateFormAdd.html',
		URL_TEMPLATE_MODAL_EDIT: '/neutrino/resources/src/back/views/modalTemplateFormEdit.html',
	});

}());