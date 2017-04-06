var bModule = angular.module("backApp");

bModule.controller('BlockManagementCtrl', function ($scope, $rootScope, BlockManagementService, $backPath) {
	$scope.init = function(folderId, pageId, activeObjectId) {
		BlockManagementService.init(folderId, pageId, activeObjectId).then(function() {
			
		});
	}
});