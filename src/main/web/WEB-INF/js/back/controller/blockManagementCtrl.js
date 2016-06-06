var module = angular.module("backApp");

module.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, PATH) {
	$scope.init = function(viewPage) {
		BlockManagementService.init(viewPage).then(function() {
			$scope.BlockManagementFacade = BlockManagementService;
		});
	}
});