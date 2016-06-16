var bModule = angular.module("backApp");

bModule.controller('TemplateModalCtrl', function($scope, $modalInstance, PATH, template, page, TemplateService) {	
	$scope.exist = function() {
		return TemplateService.getExist(page.context, template.type, template.path, template.name)
		.then(function(data) {
			$scope.scriptExist = {
				exist: data, 
				path: '\"...' + template.path + '/' + template.name + '.jsp\"',
			};
		});
	};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.updateTemplate = function(form) {
		if (form.$valid) {
			TemplateService.saveTemplate(template).then(function(data) {
				$modalInstance.close(data);
			}).catch(function(error) {
				$scope.template.errors = error;
			}).finally(function() {
				
			});
		} else {
			$modalInstance.dismiss('cancel');
		}

	};
	
	$scope.template = {
		templateModal: PATH.URL_TEMPLATE_MODAL, 
		template: template,
		errors: null, 
	};
	
	$scope.exist();
});



