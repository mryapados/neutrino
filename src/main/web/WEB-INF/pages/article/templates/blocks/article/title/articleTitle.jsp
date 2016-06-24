<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>

<my:init test="${!initialized}"/>

<h1>Article title perso</h1>
<h3>parent name = ${parentPageBlock.name}</h3>
<h3>active name = ${activeBlock.name}</h3>


<!-- <nav class="navbar navbar-inverse navbar-fixed-top"> -->
<!-- 	<div class="container-fluid"> -->
<!-- 		<div class="navbar-header"> -->
<!-- 			<button type="button" class="navbar-toggle collapsed" -->
<!-- 				data-toggle="collapse" data-target="#navbar" aria-expanded="false" -->
<!-- 				aria-controls="navbar"> -->
<!-- 				<span class="sr-only">Toggle navigation</span> <span -->
<!-- 					class="icon-bar"></span> <span class="icon-bar"></span> <span -->
<!-- 					class="icon-bar"></span> -->
<!-- 			</button> -->
<%-- 			<a class="navbar-brand" href="#"><p><c:out value="${project}" /></p></a> --%>
<!-- 		</div> -->
<!-- 		<div id="navbar" class="navbar-collapse collapse"> -->
<!-- 			<ul class="nav navbar-nav navbar-right"> -->
<!-- 				<li><a href="#">Dashboard</a></li> -->
<!-- 				<li><a href="#">Settings</a></li> -->
<!-- 				<li><a href="#">Profile</a></li> -->
<!-- 				<li><a href="#">Help</a></li> -->
<!-- 			</ul> -->
<!-- 			<form class="navbar-form navbar-right"> -->
<!-- 				<input type="text" class="form-control" placeholder="Search..."> -->
<!-- 			</form> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </nav> -->



