<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
	Objets disponibles :  
	language : Scope Request, String , en / fr / es / ..., objet fourni par le controller, provient d'un cookie.

	TO DO...

--%>

<%-- init : Obligatoire dans le JSP contenant le Doctype --%>
<my:init test="${!initialized}"/>

<!DOCTYPE html>
<html lang="${language}">
<my:head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<%-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags --%>
	<meta name="description" content="">
	<meta name="author" content="Cédric Sevestre">
	<link rel="icon" href="favicon.ico">
	
	<title><spring:message code="project.pages.home.title" arguments="${project}" /></title>
	
	<!-- Custom styles for this template -->
	<link href="<c:url value='/style/back.css'/>" rel="stylesheet">
</my:head>
<my:body>


	<section id="control">
		<header>
			<my:block position="@bo_header" />
		</header>

		<div class="container-fluid">
			<div class="row">
				<nav class="col-lg-2 col-md-2 col-sm-3 hidden-xs">
					<my:block position="@bo_nav" />
				</nav>
				<article class="col-lg-8 col-md-8 col-sm-6 col-xs-12">
					<my:block position="@bo_article" />
				</article>
				<aside class="col-lg-2 col-md-2 col-sm-3 hidden-xs">
					<my:block position="@bo_aside" />
				</aside>
			</div>
		</div>
		
		<footer>
			<my:block position="@bo_footer" />
		</footer>
	</section>



	<c:forEach var="field" items="${datas.fields}" varStatus="status">
		<p>Field = ${field.name} : ${field.type} - ${field.ofType} - ${field.inList}</p>
	</c:forEach>
	
	<c:forEach var="object" items="${datas.datas}" varStatus="status">
		<c:forEach var="field" items="${object}" varStatus="status">
			<p>key = ${field.key} - value = ${field.value}</p>
		</c:forEach>
	</c:forEach>



</my:body>
</html>