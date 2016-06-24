var bModule = angular.module("backApp");

bModule.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, PATH) {
	$scope.init = function(viewPageName, activeObjectId) {
		BlockManagementService.init(viewPageName).then(function() {
			$scope.BlockManagementFacade = BlockManagementService;
		});
	}
});