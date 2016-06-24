var bModule = angular.module('backApp');

bModule.controller('UiPositionCtrl', function($scope, BlockService, MapTemplateService, TemplateService, BlockManagementService) {
	TemplateService.getTemplate($scope.model).then(function(data) {
		var model = data;
		var position = $scope.position;
		var activeObject = $scope.activeobject;

		$scope.modelType = model.type;	
		
		BlockService.getBlocksForModelPosition(model.name, activeObject, position).then(function(data) {
			$scope.blocks = data;
		});
		
	    $scope.$on('dropEvent_' + position + '@' + model.name, function (evt, dragged, dropped) {
	        //console.log('Block : ', dragged, 'Position : ', dropped, 'Model : ', model, 'Ordered : ', dropped.ordered);
	        BlockManagementService.setMapBlock(model.name, dragged.name, dropped.name, dropped.ordered).then(function(data){
	        	if (dropped.ordered < 0){
	        		$scope.blocks.splice(0, 0, data);
	        	} else {
	        		$scope.blocks.push(data);
	        	}
			});
	    }); 
		
	    $scope.remove = function(blockIndex) {
			MapTemplateService.remove($scope.blocks[blockIndex].idMapTemplate).then(function(data) {
				$scope.blocks.splice(blockIndex, 1);
			});
		}
		
	});
	
	


});