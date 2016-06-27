<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
    	<a href="">
       		<img src="<c:url value='/image/front/logo.png'/>" />
        </a>
    </div>
    <a class="navbar-brand" href="#"><p><c:out value="${activeObject.name} - ${activeObject.id}" /></p></a>

	<div id="navbar" class="navbar-collapse collapse">
		<div class="navbar-form navbar-right">
			<c:choose>
				<c:when test="${surfer.role eq 'ROLE_PUBLIC'}">
					<a href="<c:url value='/login'/>" class="btn btn-custom" type="#"><spring:message code="project.pages.home.register" /></a>
				</c:when>
				<c:otherwise>
					<div class="btn-group" role="group">
						<img src="<c:url value='/image/front/avatar-test.png'/>" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" />
						<ul class="dropdown-menu">
						  <li><a href="#">Dropdown link</a></li>
						  <li><a href="#">Dropdown link</a></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>










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








