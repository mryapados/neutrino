var bModule = angular.module("backApp");

bModule.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, PATH) {
	$scope.init = function(pageName, activeObjectId) {
		BlockManagementService.init(pageName, activeObjectId).then(function() {
			$scope.BlockManagementFacade = BlockManagementService;
		});
	}
});