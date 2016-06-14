var module = angular.module("backApp");

module.controller('BlockSelectionCtrl', function($scope) {
	
	$scope.currentLang = '';
	$scope.switchLang = function(codeLang) {
		$scope.currentLang = codeLang;
	};

	$scope.openValue = false;
	$scope.switchDisplay = function() {
		$scope.openValue = !$scope.openValue;
	};

});



