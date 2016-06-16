(function() {
	
	var bModule = angular.module("backApp");
	bModule.directive("drag", ["$rootScope", function ($rootScope) {

        function dragStart(evt, element, dragStyle) {
            element.addClass(dragStyle);
            evt.originalEvent.dataTransfer.setData( "id", evt.target.id );
            evt.originalEvent.dataTransfer.effectAllowed = 'move';
        };

        function dragEnd(evt, element, dragStyle) {
            element.removeClass(dragStyle);
        };

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                attrs.$set('draggable', 'true');
                scope.dragStyle = attrs["dragstyle"];
                element.bind('dragstart', function (evt) {
                    $rootScope.draggedElement = scope[attrs["drag"]];
                    dragStart(evt, element, scope.dragStyle);
                });
                element.bind('dragend', function (evt) {
                    dragEnd(evt, element, scope.dragStyle);
                });
            }
        }
    }]);

	bModule.directive("drop", ['$rootScope', function ($rootScope) {

        function dragEnter(evt, element, dropStyle) {
            evt.preventDefault();
            element.addClass(dropStyle);
        };

        function dragLeave(evt, element, dropStyle) {
            element.removeClass(dropStyle);
        };

        function dragOver(evt) {
            evt.preventDefault();
        };

        function drop(evt, element, dropStyle) {
            evt.preventDefault();
            element.removeClass(dropStyle);
        };

        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                scope.dropStyle = attrs["dropstyle"];
                element.bind('dragenter', function (evt) {
                    dragEnter(evt, element, scope.dropStyle);
                });
                element.bind('dragleave', function (evt) {
                    dragLeave(evt, element, scope.dropStyle);
                });
                element.bind('dragover', dragOver);
                element.bind('drop', function (evt) {
                    drop(evt, element, scope.dropStyle);
                    var model = scope.model;
                    if (!scope[attrs['drop']]) {
                        var res = attrs['drop'].split('-');
                        var ordered = 0;
                        if (res[1] == 'top'){
                        	ordered = -1;
                        } else {
                        	ordered = 1;
                        }
                        dropData = {name: res[0], ordered:ordered};
                    } else {
                    	dropData = scope[attrs["drop"]];
                    }
                    var pathEvent = 'dropEvent_' + dropData.name + '@' + model;
                    $rootScope.$broadcast(pathEvent, $rootScope.draggedElement, dropData);

                });
            }
        }
    }]);
	
	

	bModule.directive('uiPosition', function(PATH, BlockManagementService){
		return {
			scope: {
				position: '@',
				model: '@',
			},
			restrict: 'E',
			templateUrl: PATH.URL_TEMPLATE_JS + 'ui-position.html', 
			controller: 'UiPositionCtrl', 
			link: function(scope, element, attrs){
				scope.facade = BlockManagementService;
	        },
		};
	});
	

	
	
	
	
	
}());