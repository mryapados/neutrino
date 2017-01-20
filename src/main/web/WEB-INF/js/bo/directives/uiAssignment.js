(function() {

	var fModule = angular.module("frontApp");
	fModule.directive('input', function($parse, idProvidersFilter) {
		return {
			restrict : 'E',
			require : '?ngModel',
			link : function(scope, element, attrs, ngModelController) {
				if (attrs.ngModel && attrs.value) {
					$parse(attrs.ngModel).assign(scope, idProvidersFilter(attrs.value, 'toArray'));
					ngModelController.$parsers.push(function(data) {
						// convert data from view format to model format
						return idProvidersFilter(data, 'toArray'); // converted
					});
					ngModelController.$formatters.push(function(data) {
						// convert data from model format to view format
						return idProvidersFilter(data);  // converted
					});
				}
			}
		}
	});

	fModule.directive('uiAssignment', function(PATH){
		return {
			scope: {
				field: '@',
			},
			restrict: 'E',
			transclude: true,
			templateUrl: PATH.URL_TEMPLATE_JS + 'ui-assignement.html', 
			controller: 'UiAssignmentCtrl', 
			link: function(scope, element, attrs){
				
	        },
		};
	});
	

	
	
}());