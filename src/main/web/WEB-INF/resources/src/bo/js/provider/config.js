(function(angular) {
    'use strict';
    angular.module('boApp').provider('boConfig', function() {

        var values = {
            tplPath: 'src/bo/js/templates'
        };

        return {
            $get: function() {
                return values;
            },
            set: function (constants) {
                angular.extend(values, constants);
            }
        };

    });
})(angular);
