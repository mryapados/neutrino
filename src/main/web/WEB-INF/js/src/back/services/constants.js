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