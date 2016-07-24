<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}" />

<c:if test="${!blockPreview}"><c:set var="fixedClass" value=" navbar-fixed-top"/></c:if>
<nav class="navbar navbar-default${fixedClass}">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Neutrino</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Support <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">eDoc</a></li>
						<li><a href="#">Javadoc</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Contact</a></li>
					</ul>
				</li>
				<my:block position="@bo_headerMenu" />
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="../navbar/">Default</a></li>
				<li><a href="../navbar-static-top/">Static top</a></li>
				<li class="active"><a href="./">Fixed top <span class="sr-only">(current)</span></a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>



