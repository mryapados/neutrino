var bModule = angular.module('backApp');

bModule.controller('UiPositionCtrl', function($scope, BlockManagementService, BlockService, $backPath) {
	console.log('Enter in UiPositionCtrl');

	BlockManagementService.init().then(function() {
		console.log(BlockManagementService.getFolder());
		console.log(BlockManagementService.getPage());
		console.log($scope.positionId);

		BlockService.getBlocksForModelPosition($scope.modelId, $scope.activeObjectId, $scope.positionId).then(function(data) {
			$scope.blocks = data;
		});
		
		$scope.folder = BlockManagementService.getFolder();
		$scope.page = BlockManagementService.getPage();
		$scope.activeObject = BlockManagementService.getActiveObject();
			
		$scope.getParsedBlock = function(block) {
			
			console.log('block ------------');
			console.log('Position = ' + $scope.positionId);
			console.log(block);
	
			
			return $backPath.URL_SERVER_REST + '@back/parsedblock/' + $scope.page.id + '/' + block.id + '?folderId=' + $scope.folder.id;
		}
		
		
		
		
	});
	
	
	
	
	
	
	
	
//	TemplateService.getTemplate($scope.model).then(function(data) {

		
		
		
//		var model = data;
//		var position = $scope.position;
//		var activeObject = $scope.activeobject;
//
//		$scope.modelKind = model.kind;	
//		
//		BlockService.getBlocksForModelPosition(model.name, activeObject, position).then(function(data) {
//			$scope.blocks = data;
//		});
//		
//	    $scope.$on('dropEvent_' + position + '@' + model.name, function (evt, dragged, dropped) {
//	        //console.log('Block : ', dragged, 'Position : ', dropped, 'Model : ', model, 'Ordered : ', dropped.ordered);
//	        BlockManagementService.setMapBlock(model.name, dragged.name, dropped.name, dropped.ordered).then(function(data){
//	        	if (dropped.ordered < 0){
//	        		$scope.blocks.splice(0, 0, data);
//	        	} else {
//	        		$scope.blocks.push(data);
//	        	}
//			});
//	    }); 
//		
//	    $scope.remove = function(blockIndex) {
//			MapTemplateService.remove($scope.blocks[blockIndex].idMapTemplate).then(function(data) {
//				$scope.blocks.splice(blockIndex, 1);
//			});
//		}
		
//	});
	
	


});