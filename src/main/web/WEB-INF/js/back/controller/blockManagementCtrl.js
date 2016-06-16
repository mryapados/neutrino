var bModule = angular.module("backApp");

bModule.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, PATH) {
	$scope.init = function(viewPage) {
		BlockManagementService.init(viewPage).then(function() {
			$scope.BlockManagementFacade = BlockManagementService;
		});
	}
});