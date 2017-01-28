var fModule = angular.module('frontApp');
fModule.controller('UiAssignmentCtrl', function ($scope, $uibModal, $frontPath) {
	
	console.log('in UiAssignmentCtrl');
	
	$scope.open = function(size) {
		var instance = $uibModal.open({
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement-modal.html',
			controller: 'UiAssignmentModalCtrl',
			size: size,
			resolve: {

			}
		});
		instance.result.then(function(todo) {
			if (todo) {

			}
		}, function () {
	        console.log('Modal dismissed at: ' + new Date());
	    });
	};

});

fModule.controller('UiAssignmentModalCtrl', function ($scope, $uibModalInstance) {
	
	console.log('in UiAssignmentModalCtrl');
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
	
	$scope.save = function(form) {
		if (form.$valid) {
			$uibModalInstance.close(data);
		} else {
			$uibModalInstance.dismiss('cancel');
		}
	};

});