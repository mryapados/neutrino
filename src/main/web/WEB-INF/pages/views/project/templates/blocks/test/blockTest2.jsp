<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<div class="panel panel-default">
	<div class="panel-heading panel-heading-pink"><c:out value="${activeBlock.name}"/></div>
	<div class="panel-body">
	  <p>...</p>
	</div>
</div>
