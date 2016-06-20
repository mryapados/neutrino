<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%-- Here set all necessary initialization for alone component like blocks --%>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.0.1/lodash.js"></script>


<script src="<c:url value='/js/lib/jquery-1.11.3.min.js'/>"></script>
<script
	src="<c:url value='/style/bootstrap-3.3.5-dist/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/js/lib/angular-1.5.0.min.js'/>"></script>
<script src="<c:url value='/js/lib/angular-sanitize.min.js'/>"></script>
<script src="<c:url value='/js/lib/angular-translate.min.js'/>"></script>
<script src="<c:url value='/js/lib/angular-cookies.min.js'/>"></script>
<script src="<c:url value='/js/lib/angular-resource.min.js'/>"></script>
<script src="<c:url value='/js/lib/ui-bootstrap-tpls.js'/>"></script>



<script src="//cdnjs.cloudflare.com/ajax/libs/lodash.js/4.0.1/lodash.js"
	type="text/javascript"></script>
<script
	src="http://cdn.rawgit.com/nmccready/angular-simple-logger/master/dist/angular-simple-logger.js"></script>
<script
	src="<c:url value='/js/lib/angular-google-maps/dist/angular-google-maps.js'/>"></script>


<script src="<c:url value='/js/front/app.js'/>"></script>
<script src="<c:url value='/js/front/controller/mapCtrl.js'/>"></script>




<script src="<c:url value='/js/lib/jquery-jvectormap-2.0.3.min.js'/>"></script>
<script src="<c:url value='/js/lib/jquery-jvectormap-fr-merc.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#map').vectorMap({
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
</script>









