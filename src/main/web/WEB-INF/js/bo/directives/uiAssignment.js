(function() {

	var fModule = angular.module("frontApp");

	fModule.directive('uiAssignment', function($frontPath){
		return {

			restrict: 'E',
			transclude: true,
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement.html', 
			controller: 'UiAssignmentCtrl', 
			scope:true,
			link: function(scope, element, attrs){
				scope.type = attrs['type'];
				scope.model = attrs['model'];
				scope.many = (attrs['many'] == 'true');
	        },
		};
	});

	fModule.directive('input', function($parse, idProvidersFilter, $compile) {
		return {
			restrict : 'E',
			require : '?ngModel',
		    controller: function ( $scope, $element, $attrs ) {
		        if ($attrs.assign){
		        	var many = false;
		        	if ($attrs.many){
		        		many = $attrs.many;
		        	}
			        var el = $compile('<data-ui-assignment type="' + $attrs.assign + '" model="' + $attrs.ngModel + '" many="' + many + '"/></data-ui-assignment>')( $scope );
			        $element.parent().append(el);
		        }
		    },
			
			link : function(scope, element, attrs, ngModelController) {
				if (attrs.ngModel && attrs.value && attrs.assign) {
					
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
	


	
}());