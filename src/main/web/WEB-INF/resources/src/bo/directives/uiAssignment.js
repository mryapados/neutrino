(function() {

	var fModule = angular.module("frontApp");

	fModule.directive('uiAssignment', function($parse, $frontPath){
		return {
			restrict: 'E',
			require : '?ngModel',
			transclude: true,
			templateUrl: $frontPath.URL_TEMPLATE_JS + 'ui-assignement.html', 
			controller: function ( $scope, UiAssignmentService ) {
				$scope.open = function(size) {
					UiAssignmentService.getObjects($scope[$scope.model], $scope.type, $scope.id, $scope.field, $scope.kind, $scope.disablePreChecked, size).then(function(objects) {
						$parse($scope.model).assign($scope.$parent, objects);
					})
					.catch(function(error){
						console.log(error.status);
						console.log(error);
					});
				};
			}, 
			scope : true,
			link: function(scope, element, attrs, ngModelController){
				scope.type = attrs['type'];
				scope.field = attrs['field'];
				scope.id = attrs['id'];
				scope.model = attrs['ngModel'];
				scope.kind = attrs['kind'];
				scope.disablePreChecked = (attrs['disablePreChecked'] == 'true');
	        },
		};
	});

	fModule.directive('input', function($parse, idProvidersFilter, $compile, $http, $frontPath) {
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
		        	var kind = $attrs.kind;
		        	if (kind === undefined) kind = '';
		        	var model = $attrs.ngModel;
			        var uiAssign = $compile('<data-ui-assignment type="' + type + '" field="' + field + '" id="' + id + '" ng-model="' + model + '" kind="' + kind + '" disable-pre-checked="' + disablePreChecked + '"/></data-ui-assignment>')( $scope );
			        $element.parent().append(uiAssign);

			        var modelText = $attrs.ngModel + 'Text';
			        var uiAssignText = $compile('<ul class="linked"><li data-ng-repeat="r in ' + modelText + ' | orderBy: \'name\'"><a class="linked" href="#">{{r.name}}</a></li><li data-ng-show="' + model+ '.length - ' + modelText + '.length > 0"><strong><a href="#">{{' + model+ '.length - ' + modelText + '.length}} <span>Others results...</span></a></strong></li></ul>')( $scope );
			        $element.parent().append(uiAssignText);

			        $scope.$watch(model, function() {			        	
			        	var max = 5;
						var objects = $scope[model];
			      		if (objects && objects.length > 0){
			      			if (!max) max = objects.length;
			      			else if (objects.length < max) max = objects.length;
			      			
			      			//Le type de la collection est identifié par le 1er de la liste
			      			var type = objects[0].type;
			      			
			      			//Chaque id est récupéré
			      			var ids = [];
			      			for(var i = 0; i < max; i++) {
			      				if (objects[i].id){
			      					ids.push(objects[i].id);
			      				}
			      			}
			      			console.log(ids);
			      			//Récupère la liste des objets avec leur nom via une requête au serveur
			      			if (ids.length > 0){
					      		$http.get($frontPath.URL_SERVER_REST + 'bo/objects/' + type, {params:{'id': ids}}).then(function(data) {
					      			$scope[modelText] = data.data;
					      			
								})
			      			}

			      		}

			        });

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