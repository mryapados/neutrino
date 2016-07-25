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
				<li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <span class="fa fa-user fa-fw"></span> <span class="fa fa-caret-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><span class="fa fa-user fa-fw"></span> <spring:message code="bo.connexion.user-profile" /></a></li>
                        <li><a href="#"><span class="fa fa-gear fa-fw"></span> <spring:message code="bo.connexion.settings" /></a></li>
                        <li class="divider"></li>
                        <li><a href="<c:url value='/logout'/>"><span class="fa fa-sign-out fa-fw"></span> <spring:message code="bo.connexion.logout" /></a></li>
                    </ul>
                </li>
				<li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                       <span class="lang-sm lang-lbl-full" lang="${language}"></span> <span class="caret"></span>
                    </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="ar"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="be"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="bg"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="cs"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="da"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="de"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="el"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="en"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="es"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="et"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="fi"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="fr"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="ga"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="hi"></span></a></li>
						<li><a href="#"><span class="lang-sm lang-lbl-full" lang="hr"></span></a></li>
					</ul>
				</li>
			</ul>
		</div>

	</div>
</nav>



