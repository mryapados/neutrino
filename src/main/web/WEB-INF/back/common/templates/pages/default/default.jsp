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
</my:head>
<my:body>

	

	<section id="control">
		<header>
			<my:block position="@bo_header" />
		</header>

		<div id="page" class="container-fluid">
			<div class="row">
			
			
				
				<my:hasBlock position="@bo_nav" var="hasBlockA"/>
				
			
				<c:set var="showColA" value="X"/>
				<c:set var="showColB" value="X"/>
				<c:set var="showColC" value="X"/>
				<c:set var="showCode" value="${showColA}${showColB}${showColC}"/>
				<c:choose>
					<c:when test="${showCode eq 'XXX'}">
						<c:set var="classA" value="col-lg-2 col-md-2 col-sm-3 col-xs-12"/>
						<c:set var="classB" value="col-lg-8 col-md-8 col-sm-6 col-xs-12"/>
						<c:set var="classC" value="col-lg-2 col-md-2 col-sm-3 col-xs-12"/>
					</c:when>
					<c:when test="${showCode eq 'XXO'}">
						<c:set var="classA" value="col-lg-2 col-md-2 col-sm-3 col-xs-12"/>
						<c:set var="classB" value="col-lg-10 col-md-10 col-sm-9 col-xs-12"/>
					</c:when>
					<c:when test="${showCode eq 'XOO'}">
						<c:set var="classA" value="col-lg-12 col-md-12 col-sm-12 col-xs-12"/>
					</c:when>
					<c:when test="${showCode eq 'OXX'}">
						<c:set var="classB" value="col-lg-10 col-md-10 col-sm-9 col-xs-12"/>
						<c:set var="classC" value="col-lg-2 col-md-2 col-sm-3 col-xs-12"/>
					</c:when>
					<c:when test="${showCode eq 'OOX'}">
						<c:set var="classC" value="col-lg-12 col-md-12 col-sm-12 col-xs-12"/>
					</c:when>
					<c:when test="${showCode eq 'XOX'}">
						<c:set var="classA" value="col-lg-6 col-md-6 col-sm-6 col-xs-12"/>
						<c:set var="classC" value="col-lg-6 col-md-6 col-sm-6 col-xs-12"/>
					</c:when>
					<c:when test="${showCode eq 'OXO'}">
						<c:set var="classB" value="col-lg-12 col-md-12 col-sm-12 col-xs-12"/>
					</c:when>
				</c:choose>
				
				<c:if test="${showColA eq 'X'}">
					<nav class="${classA}">
						<my:block position="@bo_nav" />
					</nav>
				</c:if>
				<c:if test="${showColB eq 'X'}">
					<article class="${classB}">
						<my:block position="@bo_article" />
					</article>
				</c:if>
				<c:if test="${showColC eq 'X'}">
					<aside class="${classC}">
						<my:block position="@bo_aside" />
					</aside>
				</c:if>
				
			</div>
		</div>
		
		<footer>
			<my:block position="@bo_footer" />
		</footer>
	</section>







</my:body>
</html>