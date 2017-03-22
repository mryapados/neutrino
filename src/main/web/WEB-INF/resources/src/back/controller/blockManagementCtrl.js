var bModule = angular.module("backApp");

bModule.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, $backPath) {
	$scope.init = function(folderName, pageName, activeObjectId) {
		BlockManagementService.init(folderName, pageName, activeObjectId).then(function() {
			$scope.BlockManagementFacade = BlockManagementService;
		});
	}
});