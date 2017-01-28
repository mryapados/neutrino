(function() {
	var bModule = angular.module('backServices');
	bModule.constant('$backPath', {
		URL_SERVER_REST: '/neutrino/',
		URL_TEMPLATE_JS: '/neutrino/js/back/templates/',
		URL_TEMPLATE_MODAL: '/neutrino/js/back/views/templateForm.html',
		URL_TEMPLATE_MODAL_ADD: '/neutrino/js/back/views/modalTemplateFormAdd.html',
		URL_TEMPLATE_MODAL_EDIT: '/neutrino/js/back/views/modalTemplateFormEdit.html',
	});

}());