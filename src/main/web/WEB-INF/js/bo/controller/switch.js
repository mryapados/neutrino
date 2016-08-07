'use strict';

var fModule = angular.module('frontApp');
fModule.controller('SwitchCtrl', ['$scope',
  function($scope) {

    $scope.isSelected = 'nope';
    $scope.onText = 'ASC';
    $scope.offText = 'DESC';
    $scope.isActive = true;
    $scope.size = 'mini';
    $scope.animate = true;
    $scope.radioOff = true;
    $scope.handleWidth = "auto";
    $scope.labelWidth = "auto";
    $scope.inverse = true;

    $scope.$watch('isSelected', function() {
      $log.info('Selection changed.');
    });

    $scope.toggle = function() {
      $scope.isSelected = $scope.isSelected === 'yep' ? 'nope' : 'yep';
    };

    $scope.setUndefined = function() {
      $scope.isSelected = undefined;
    };

    $scope.toggleActivation = function() {
      $scope.isActive = !$scope.isActive;
    }

  }
]);