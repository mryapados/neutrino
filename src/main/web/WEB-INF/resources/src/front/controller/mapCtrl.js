	

var fModule = angular.module("frontApp");
fModule.controller("MapCtrl", function($scope, uiGmapGoogleMapApi) {
    uiGmapGoogleMapApi.then(function(maps) {
    	$scope.googleVersion = maps.version;
    	
    	$scope.map = { center: { latitude: 45, longitude: -73 }, zoom: 8 };
        $scope.options = {scrollwheel: false};

    	$scope.mapShow = true;
    });
});
	
	

	
