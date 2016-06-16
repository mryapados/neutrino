(function() {
	
	
	var fModule = angular.module('frontApp', ['uiGmapgoogle-maps']);
	fModule.config(function(uiGmapGoogleMapApiProvider) {
	    uiGmapGoogleMapApiProvider.configure({
	        key: 'AIzaSyCZJrwJoh_Nu95T5Wp2iZFKbe3W00vFMck',
//	        v: '3.20', //defaults to latest 3.X anyhow
	        libraries: 'weather,geometry,visualization'
	    });
	})
	
	
//	var fModule = angular.module('frontApp', [ 'ui.bootstrap', 'ngResource', 'uiGmapGoogleMapApi' ]);
//	fModule.config(function(uiGmapGoogleMapApiProvider) {
//	    uiGmapGoogleMapApiProvider.configure({
//	        //    key: 'your api key',
//	        v: '3.20', //defaults to latest 3.X anyhow
//	        libraries: 'weather,geometry,visualization'
//	    });
//	})


	
	
	
}());