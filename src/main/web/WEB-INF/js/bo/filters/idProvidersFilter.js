(function() {
	
	var fModule = angular.module("frontApp");

	fModule.filter('idProviders', function () {
		return function(object, method) {
			if (!method) method = 'toString';
			if (method == 'toArray'){
		    	var res = object.split(",");
		    	var idproviders = [];
		    	angular.forEach(res, function(value) {
		    		if (value != ''){
			    		var idprovider = value.split("_");
			    		idproviders.push({type:idprovider[0], id:Number(idprovider[1])});  
		    		}
		    	});
		    	return idproviders;
			} else {
				var res = '';
		    	angular.forEach(object, function(value) {
		    		res += value.type + '_' + value.id + ',';
		    	});
		    	return res;
			}
		};
	});

}());