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

	fModule.filter('strings', function () {
		return function(object, method) {
			if (!method) method = 'toString';
			if (method == 'toArray'){

				if (object.charAt(0) == '[' && object.charAt(object.length - 1) == ']'){
					var res = object.substring(1, object.length - 1); 
			    	var res = res.split(",");
			    	var strings = [];
			    	angular.forEach(res, function(value) {
			    		if (value != ''){
				    		strings.push(value);  
			    		}
			    	});
			    	return strings;
				} else {
					var strings = [];
					strings.push(object);
					return strings;
				}

			} else {
				if (object && object.length == 1) return object[0];
				var res = '';
		    	angular.forEach(object, function(value) {
		    		var encodedStr = value.replace(/[\u00A0-\u9999<>\&]/gim, function(i) {
		    			return '&#'+i.charCodeAt(0)+';';
		    		})
		    		encodedStr = encodedStr.replace(",", "&c"); + ',';
		    		encodedStr = encodedStr.replace("[", "&o"); + ',';
		    		encodedStr = encodedStr.replace("]", "&f"); + ',';
		    		
		    		res += encodedStr + ',';
		    		
		    	});
		    	if (res != ''){
		    		res = '[' + res + ']';
		    	}
		    	return res;
			}
		};
	});

	
	
	
	
	
	


}());