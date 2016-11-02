var fModule = angular.module('frontApp');
fModule.controller('WysiwygEditorCtrl', function ($scope, textAngularManager) {
    $scope.version = textAngularManager.getVersion();
    $scope.versionNumber = $scope.version.substring(1);
    $scope.disabled = false;
});
