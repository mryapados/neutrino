(function() {

	var fModule = angular.module("frontApp");

	fModule.directive('uiAssignment', function($parse, $frontPath){
		return {
			restrict: 'E',
			require : '?ngModel',
			transclude: true,
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement.html', 
			controller: ['$scope', 'UiAssignmentService', function UiAssignmentCtrl($scope, UiAssignmentService) {
				$scope.open = function(size) {
					UiAssignmentService.getObjects($scope[$scope.model], $scope.type, $scope.id, $scope.field, $scope.disablePreChecked, size).then(function(objects) {
						console.log(objects);
						
						$parse('testage').assign($scope, 'aaaaaaaaaaaa');
						$parse($scope.model).assign($scope.$parent, objects);
						
					})
					.catch(function(error){
						console.log(error.status);
						console.log(error);
					});
	
				};
			}], 
			scope : true,
			link: function(scope, element, attrs, ngModelController){
				scope.type = attrs['type'];
				scope.field = attrs['field'];
				scope.id = attrs['id'];
				scope.model = attrs['ngModel'];
				scope.disablePreChecked = (attrs['disablePreChecked'] == 'true');
				
				//scope.values = $parse(attrs['ngModel']);
				
				//scope.values = scope.$parent[attrs['ngModel']];
				
				$parse('testage').assign(scope, 'ttt');
				
//				scope.values = 'begin';
//				scope[scope.model] = 'ttt';
				
				

				
				
				
				
	        },
		};
	});

	fModule.directive('input', function($parse, idProvidersFilter, $compile) {
		return {
			restrict : 'E',
			require : '?ngModel',
		    controller: function ( $scope, $element, $attrs ) {
		        if ($attrs.assign){
		        	var disablePreChecked = false;
		        	if ($attrs.disablePreChecked){
		        		disablePreChecked = $attrs.disablePreChecked;
		        	}
		        	
		        	var res = $attrs.assign.split('_');
		        	var type = res[0];
		        	var id = res[1];
		        	var field = res[2];
			        var el = $compile('<data-ui-assignment type="' + type + '" field="' + field + '" id="' + id + '" ng-model="' + $attrs.ngModel + '" disable-pre-checked="' + disablePreChecked + '"/></data-ui-assignment>')( $scope );
			        $element.parent().append(el);
		        }
		    },
			
			link : function(scope, element, attrs, ngModelController) {
				if (attrs.ngModel && attrs.assign) {
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