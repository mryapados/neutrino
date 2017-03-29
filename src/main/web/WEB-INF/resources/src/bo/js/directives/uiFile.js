(function() {

	var fModule = angular.module("frontApp");

	fModule.directive('uiFile', function($parse, $frontPath){
        return {
            restrict: 'EA',
            scope: {
            	url: '=',
            },
			controller: function ($scope) {
				$scope.src = $scope.url;
				var filename = $scope.url.substring($scope.url.lastIndexOf('/')+1);
				$scope.name = filename;

			}, 
            templateUrl: $frontPath.URL_TEMPLATE_JS + '/ui-file.html',
            link: function(scope, element, attrs) {

            }
        };
	});

    fModule.filter('extension', function($frontPath) {
        return function(url) {
        	var basePath = $frontPath.URL_TEMPLATE_JS + 'resources/';
        	var extension = url.split('.').pop().toLowerCase();
        	var extensions = 
            {
        		'png': url,
        		'jpg': url,
        		'jpeg': url,
        		'gif': url,
        		'bmp': url,
        		'pdf': basePath + 'pdf.png',
        		'ND': basePath + 'nd.ico',
            }
        	var result = extensions[extension];
            if (result == null){
            	result = extensions['ND'];
            }
            return result;
        };
     });
    



    


	
}());