<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<c:set var="position" value="${param.position}"/>
<div class="btn-toolbar" role="toolbar" style="${position eq 'top' ? ' margin-bottom:15px;' : ' margin-top:15px;'}">
	<div class="btn-group" role="group" aria-label="...">
		<div class="btn-group" role="group">
		
			<a href="<c:url value='/bo/edit?type=${objectType}&id=${object.id}' />" class="btn btn-primary">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
				<s:message code="bo.view.button.edit" text="Edit" />
			</a>
			<a href="TODO NEW" class="btn btn-primary">
				<span class="glyphicon glyphicon-paste" aria-hidden="true"></span>
				<s:message code="bo.view.button.duplicate" text="Duplicate" />
			</a>

		</div>
	</div>
	
	<div class="btn-group pull-right" role="group" aria-label="...">
		<div class="btn-group" role="group">
		
			<a href="<c:url value='/bo/list?type=${objectType}' />" class="btn btn-primary">
				<span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>
				<s:message code="bo.edit.button.goback" text="Come back" />
			</a>

		</div>
	</div>
</div>