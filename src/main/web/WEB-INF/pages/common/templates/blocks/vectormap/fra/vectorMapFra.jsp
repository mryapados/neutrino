<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>

<my:init test="${!initialized}"/>

<c:set var="mapName" value="${activeBlock.name}" />
<div style="height:500px;" id="${mapName}">


</div>

<my:discrimin name="vectorMap">
	<c:url var="jsurl" value='/resources/lib/jquery-jvectormap-2.0.3.min.js'/>
	<my:script src="${jsurl}"></my:script>
	
	<c:url var="jsurl" value='//resources/lib/jquery-jvectormap-fr-merc.js'/>
	<my:script src="${jsurl}"></my:script>
</my:discrimin>

<my:script>
	$(document).ready(function() {
		$('#<c:out value="${mapName}"/>').vectorMap({
			map : 'fr_merc',
			backgroundColor : "#ffffff",
			regionStyle: {
				  initial: {
					    fill: '#007A91',
					    "fill-opacity": 1,
					    stroke: 'none',
					    "stroke-width": 0,
					    "stroke-opacity": 1
					  },
					  hover: {
					    "fill-opacity": 0.8,
					    cursor: 'pointer'
					  },
					  selected: {
					    fill: 'yellow'
					  },
					  selectedHover: {
					  }
					},
			
			regionLabelStyle : {
				initial : {
					fill : '#ffffff'
				},
				hover : {
					fill : 'black'
				}
			},

			labels : {
				regions : {
					render : function(code) {
						var doNotShow = [ 'FR-75', 'FR-91', 'FR-92', 'FR-93', 'FR-94', 'FR-95' ];

						if (doNotShow.indexOf(code) === -1) {
							return code.split('-')[1];
						}
					},
					offsets : function(code) {
						return {
							'84' : [ -10, 10 ],
							'ID' : [ 0, 40 ],
							'OK' : [ 25, 0 ],
							'LA' : [ -20, 0 ],
							'FL' : [ 45, 0 ],
							'KY' : [ 10, 5 ],
							'VA' : [ 15, 5 ],
							'MI' : [ 30, 30 ],
							'AK' : [ 50, -25 ],
							'HI' : [ 25, 50 ]
						}[code.split('-')[1]];
					}
				}
			}

		});
	});
</my:script>