//const URL_SERVER_REST = "/geophoto/";
//const DEBUG = true;
//
//(function() {
//
//	
//	
//	var app = angular.module("geophotoBack", []);
//	
//    app.controller("BlockSelectionController", function ($scope, $http) {
//    	$scope.openValue = false;
//    	$scope.switchDisplay = function() {
//    		$scope.openValue = !$scope.openValue;
//    	};
//        $scope.getBlocks = function () {
//    	    $http.get(URL_SERVER_REST + '@back/blocks/')
//            .success(function(data) {
//            	var listAvailableBlocks = [];
//                angular.forEach(data, function(value, key){
//                	listAvailableBlocks.push(value);
//                });
//                $scope.listAvailableBlocks = listAvailableBlocks;
//            })
//            .error(function() {
//            	console.log('error');
//            });
//        };
//        $scope.getBlocks();
//    });
//
//    
//    app.directive("drag", ["$rootScope", function ($rootScope) {
//
//        function dragStart(evt, element, dragStyle) {
//            element.addClass(dragStyle);
//            evt.originalEvent.dataTransfer.setData( "id", evt.target.id );
//            evt.originalEvent.dataTransfer.effectAllowed = 'move';
//        };
//
//        function dragEnd(evt, element, dragStyle) {
//            element.removeClass(dragStyle);
//        };
//
//        return {
//            restrict: 'A',
//            link: function (scope, element, attrs) {
//                attrs.$set('draggable', 'true');
//                scope.dragStyle = attrs["dragstyle"];
//                element.bind('dragstart', function (evt) {
//                    $rootScope.draggedElement = scope[attrs["drag"]];
//                    dragStart(evt, element, scope.dragStyle);
//                });
//                element.bind('dragend', function (evt) {
//                    dragEnd(evt, element, scope.dragStyle);
//                });
//            }
//        }
//    }]);
//
//    app.directive("drop", ['$rootScope', '$http', function ($rootScope, $http) {
//
//        function dragEnter(evt, element, dropStyle) {
//            evt.preventDefault();
//            element.addClass(dropStyle);
//        };
//
//        function dragLeave(evt, element, dropStyle) {
//            element.removeClass(dropStyle);
//        };
//
//        function dragOver(evt) {
//            evt.preventDefault();
//        };
//
//        function drop(evt, element, dropStyle) {
//            evt.preventDefault();
//            element.removeClass(dropStyle);
//        };
//
//        return {
//            restrict: 'A',
//            link: function (scope, element, attrs) {
//                scope.dropStyle = attrs["dropstyle"];
//                element.bind('dragenter', function (evt) {
//                    dragEnter(evt, element, scope.dropStyle);
//                });
//                element.bind('dragleave', function (evt) {
//                    dragLeave(evt, element, scope.dropStyle);
//                });
//                element.bind('dragover', dragOver);
//                element.bind('drop', function (evt) {
//                    drop(evt, element, scope.dropStyle);
//
//                    if (!scope[attrs['drop']]) {
//                      	listPositions = scope['listPositions'];
//                      	var ordered = 0;
//                        var res = attrs['drop'].split('-');
//                        dropData = listPositions[res[0]];
//                        if (res[1]){
//                            if (res[1] == 'top'){
//                            	ordered = -1;
//                            } else {
//                            	ordered = 1;
//                            }
//                        }
//                        dropData.ordered = ordered;
//                    } else {
//                    	dropData = scope[attrs["drop"]];
//                    }
//                    $rootScope.$broadcast('dropEvent', $rootScope.draggedElement, dropData);
//
//                });
//            }
//        }
//    }]);
//
//    app.controller('BlockManagementController', ["$scope", "$http", "$rootScope", function ($scope, $http, $rootScope) {
//    	$scope.init = function(model)
//    	{
//    	    $scope.getPositions = function () {
//        	    $http.get(URL_SERVER_REST + '@back/positions')
//                .success(function(data) {
//                	$scope.listPositions = data;
//                })
//                .error(function() {
//                	console.log('error');
//                });
//            };
//            $scope.getPositions();
//
//            $scope.getListBlocks = function () {
//        	    $http.get(URL_SERVER_REST + '@back/blocks/' +  model)
//                .success(function(data) {
//                	$scope.listBlocks = data;
//                })
//                .error(function() {
//                	console.log('error');
//                });
//            };
//            $scope.getListBlocks();  
//    	    
//            $scope.removeMapBlock = function (idTemplateBlock) {
//        		var res = $http.delete(URL_SERVER_REST + '@back/removemapblock/' + idTemplateBlock);
//        		res.success(function() {
//        			$scope.getListBlocks();
//        		});
//        		res.error(function() {
//        			alert('Error');
//        		});		
//            };   
//            
//            
//            $scope.getParsedBlock = function (modelName, blockName, language) {
//            	return URL_SERVER_REST + '@back/parsedblock/' + modelName + '/' + blockName + '/' + language;
//            };
//
//            $scope.setMapBlock = function (modelName, blockName, positionName, ordered) {	
//        		var dataObj = {
//        				modelName : modelName,
//        				blockName : blockName,
//        				positionName : positionName, 
//        				ordered : ordered
//        		};	
//        		var res = $http.post(URL_SERVER_REST + '@back/addmapblock', dataObj);
//        		res.success(function(data, status, headers, config) {
//        			$scope.getListBlocks();
//        		});
//        		res.error(function(data, status, headers, config) {
//        			alert( "failure message: " + JSON.stringify({data: data}));
//        		});		
//            };          
//
//            $rootScope.$on('dropEvent', function (evt, dragged, dropped) {
//                if (DEBUG) console.log('Block : ', dragged, 'Position : ', dropped, 'Model : ', model, 'Ordered : ', dropped.ordered);
//                $scope.setMapBlock(model, dragged.name, dropped.name, dropped.ordered);
//                $scope.$apply();
//            }); 
//            
//            
//    	};
//    	
//    }]);
//    
//
//    
//}());
