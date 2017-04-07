var bModule = angular.module('backApp');

bModule.controller('UiPositionCtrl', function($scope, $uibModal, BlockManagementService, TemplateService, BlockService, $backPath) {
	BlockManagementService.init().then(function() {
		$scope.debug = false;
		
		BlockService.getBlocksForModelPosition($scope.modelId, $scope.activeObjectId, $scope.positionId).then(function(data) {
			$scope.blocks = data;
		});
		
		TemplateService.getTemplate($scope.modelId).then(function(data) {
			$scope.model = data;
		});
		
		$scope.folder = BlockManagementService.getFolder();
		$scope.page = BlockManagementService.getPage();
		$scope.activeObject = BlockManagementService.getActiveObject();
		
		var activeObjectId = $scope.activeObject == null ? '' : $scope.activeObject.id;
		$scope.getParsedBlock = function(block) {
			return $backPath.URL_SERVER_REST + '@back/parsedblock/' + $scope.page.id + '/' + block.id + '/' + activeObjectId + '?folderId=' + $scope.folder.id;
		}
		
	    $scope.$on('dropEvent_' + $scope.positionId + '@' + $scope.modelId, function (evt, block, ordered) {
	    	console.log($scope.positionId + '@' + $scope.modelId, $scope.page, block, ordered);

			var instance = $uibModal.open({
				templateUrl: $backPath.URL_TEMPLATE_MODAL_CHOICE_SCOPE,
				controller: function ( $scope, $uibModalInstance ) {
	
					$scope.scopeActiveObject = function() {
						$uibModalInstance.close('ACTIVEOBJECT');
					};	
					$scope.scopeModel = function() {
						$uibModalInstance.close('MODEL');
					};	
					
				}, 
			});
			instance.result.then(function(choice) {
				var scopeChoice = null;
				if (choice == 'MODEL') scopeChoice = $scope.modelId;
				else if (choice == 'ACTIVEOBJECT') scopeChoice = activeObjectId;
				
				console.log(scopeChoice);
				
		        BlockManagementService.setMapBlock(scopeChoice, block.id, $scope.positionId, ordered).then(function(data){
		        	if (ordered < 0){
		        		$scope.blocks.splice(0, 0, data);
		        	} else {
		        		$scope.blocks.push(data);
		        	}
				});
			});
	    	

	    }); 
		
	    $scope.remove = function(blockIndex) {
	    	BlockManagementService.removeMapBlock($scope.blocks[blockIndex].idMapTemplate).then(function(data) {
				$scope.blocks.splice(blockIndex, 1);
			});
		}
	    
	    
	    
	    
//        self.getPathTemplateForm = function() {
//        	return $backPath.URL_TEMPLATE_MODAL;
//        }
        
        $scope.open = function(blockId) {
			var instance = $uibModal.open({
				templateUrl: $backPath.URL_TEMPLATE_MODAL_EDIT,
				controller: 'TemplateModalCtrl',
				resolve: {
					template: function(){
						return blockId;
					}, 
					page: function() { return $scope.page;}, 
				}
			});
			instance.result.then(function(savedTemplate) {
				
				if (templates) {
					// TODO Update templates list
					
					//var oldTemplate =getByIdFilter(templates, blockId);
					//var index = templates.indexOf(oldTemplate);

					//templates[index] = savedTemplate;
	
				
	
				}

				
				
				
			});
		};
	});
	

});






