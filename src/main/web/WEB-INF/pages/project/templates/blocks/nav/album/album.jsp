<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<div class="panel panel-default">
	<div class="panel-heading panel-heading-yellow">
		<spring:message code="project.block.album.title" />
	</div>
	
	<ul class="list-group">
		<li class="list-group-item">
			<span class="badge">14</span>
			Corse 2015
		</li>
		<li class="list-group-item">
			<span class="badge">5</span>
			Noël 2014
		</li>
		<li class="list-group-item">
			<span class="badge">8</span>
			Marseille
		</li>
		<li class="list-group-item">
			<span class="badge">32</span>
			Road Trip US 2013
		</li>
	</ul>
	
</div>
