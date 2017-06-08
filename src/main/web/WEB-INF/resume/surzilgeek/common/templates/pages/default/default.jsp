<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<%-- 
	Objets disponibles :  
	language : Scope Request, String , en / fr / es / ..., objet fourni par le controller, provient d'un cookie.
	activePage : Scope Request.
	TO DO...
--%>

<%-- init : Obligatoire dans le JSP contenant le Doctype --%>
<my:init test="${!initialized}"/>

<!DOCTYPE html>
<html lang="${language}">
<!--[if IE 9]>
<html class="ie9" lang="${language}">    <![endif]-->
<!--[if IE 8]>
<html class="ie8" lang="${language}">    <![endif]-->
<my:head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name=viewport content="width=device-width, initial-scale=1">
	
	<meta name="description" content="<c:out value='${activePage.metaDescription}'/>">
	<meta name="keywords" content="<c:out value='${activePage.metaKeyWords}'/>">
	<meta name="author" content="Cédric Sevestre">
	
	<title><c:out value="${activePage.metaTitle}"/></title>
</my:head>
<my:body>

    <div class="main-wrapper home-one">
    
        <div id="navigation" class="menu-one">
            <div class="navbar navbar-fixed-top">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <div class="navbar-brand logo">
                        <a href="index.html"><img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/logo.png'/>" alt="Image" ></a> 
                    </div> 
                </div>

                <nav role="navigation" aria-label="<s:message code='menu.main' text='Main menu' />" id="mainmenu" class="collapse navbar-collapse">
                	<ul class="nav navbar-nav">
                		<my:block position="resume_nav" /> 
                	</ul>
                </nav>
            </div><!-- navbar -->                                
        </div><!-- navigation -->

		<header>
			<my:block position="resume_header" />
		</header>

		<main id="main" role="main">
			<my:block position="resume_main" />
		</main>

        <footer id="footer">
        	<my:block position="resume_footer" />
        </footer><!-- footer -->
    </div><!-- main wrapper -->


</my:body>
</html>