<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>

<my:init test="${!initialized}"/>

<div data-ng-controller="MapCtrl">
	<div id="map_canvas" ng-if="mapShow">

	    <data-ui-gmap-google-map center="map.center" zoom="map.zoom" options="options">

	    </data-ui-gmap-google-map>

	</div>
</div>