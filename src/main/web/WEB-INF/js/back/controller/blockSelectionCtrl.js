var module = angular.module("backApp");

module.controller('BlockSelectionCtrl', function($scope) {
	
	$scope.openValue = false;
	$scope.switchDisplay = function() {
		$scope.openValue = !$scope.openValue;
	};

});



