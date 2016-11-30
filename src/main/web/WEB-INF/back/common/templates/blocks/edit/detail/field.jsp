<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<c:set var="FINAL_MAX_ELEMENT" value="3" />

<c:choose>
	<c:when test="${finalFieldType eq 'INTEGER'}">
		<form:input type="number" class="form-control" path="${finalField.name}" />
	</c:when>
	<c:when test="${finalFieldType eq 'VARCHAR50'}">
		<c:choose>
			<c:when test="${not empty finalField.enumDatas}">
				<form:select class="form-control" path="${finalField.name}">
				    <form:option value="0" label="Select One" />
				    <form:options items="${finalField.enumDatas}" />
				</form:select>
			</c:when>
			<c:otherwise>
				<form:input class="form-control" type="text" path="${finalField.name}"/>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${finalFieldType eq 'VARCHAR255'}">
		<c:set var="fieldError"><form:errors path="${finalField.name}"/></c:set>
		<div class="form-group${not empty fieldError ? ' has-error has-feedback' : ''}">
			<form:textarea cssClass="form-control" rows="2" path="${finalField.name}"/>
			<c:if test="${not empty fieldError}">
				<span class="glyphicon glyphicon-remove form-control-feedback"></span>
			</c:if>
		</div>
		<c:if test="${not empty fieldError}">
			<div class="alert alert-danger"><strong>Error !</strong> ${fieldError}</div>
		</c:if>
	</c:when>
	<c:when test="${finalFieldType eq 'TEXT'}">	
		<form:textarea class="form-control" rows="5" path="${finalField.name}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'HTML'}">	
		<jsp:include page="wysiwyg.jsp" />
	</c:when>
	<c:when test="${finalFieldType eq 'DATETIME'}">
		<jsp:include page="datetime.jsp" />
	</c:when>
	<c:when test="${finalFieldType eq 'TOBJECT' || finalFieldType eq 'NTOBJECT'}">
		<a class="linked" href="<c:url value='/bo/view/?type=${finalObject.objectType}&id=${finalObject.id}' />"><c:out value="${finalObject.name}"/></a>
	</c:when>
	<c:when test="${finalFieldType eq 'OBJECT'}">
		<c:choose>
			<c:when test="${finalObject.objectType eq 'Lang'}">
				<span class="lang-sm lang-lbl" lang="${finalObject.code}"></span>
			</c:when>
			<c:when test="${finalObject.objectType eq 'MapTemplate'}">
				<c:choose>
					<c:when test="${finalParentObject.id eq finalObject.model.id}">
						<c:set var="template" value="${finalObject.block}" />
					</c:when>
					<c:otherwise>
						<c:set var="template" value="${finalObject.model}" />
					</c:otherwise>
				</c:choose>
				<a class="linked" href="<c:url value='/bo/view/?type=Template&id=${template.id}' />"><c:out value="${template.name}"/></a> / <a class="linked" href="<c:url value='/bo/view/?type=Position&id=${finalObject.position.id}' />"><c:out value="${finalObject.position.name}"/></a>
			</c:when>
			<c:otherwise>
				<a class="linked" href="<c:url value='/bo/view/?type=${finalObject.objectType}&id=${finalObject.id}' />"><c:out value="object"/></a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${finalFieldType eq 'COLLECTION'}">
		<c:set var="collection" value="${finalObject}" />
		<c:set var="size" value="${fn:length(collection)}" />
		<c:if test="${size > 0}">
			<ul class="linked">
				<c:set var="max" value="${size}" />
				
				<c:if test="${max > FINAL_MAX_ELEMENT}">
					<c:set var="max" value="${FINAL_MAX_ELEMENT}" />
				</c:if>
				<c:forEach var="i" begin="0" end="${max - 1}">
					<c:set var="finalObject" value="${collection.toArray()[i]}" scope="request" />
					<c:set var="finalFieldType" value="${finalField.ofType}" scope="request" />
					<li>
						<jsp:include page="field.jsp" />
					</li>
					<c:remove var="finalObject"/>
					<c:remove var="finalFieldType"/>
				</c:forEach>
				<c:if test="${size > FINAL_MAX_ELEMENT}">
					<li>
						<strong><a href="#"><c:out value="${size - FINAL_MAX_ELEMENT}"/> <s:message code="bo.otherresults" text="Others results..." /></a></strong>
					</li>
				</c:if>
			</ul>
		</c:if>
	</c:when>
	<c:when test="${finalFieldType eq 'ENUM'}">
		<c:forEach var="item" items="${finalField.enumDatas}">
			<label class="radio-inline"><form:radiobutton path="${finalField.name}" value="${item}" /><s:message code="bo.field.enum.${item}" text="${item}" /></label>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:out value="${finalObject}"/>
	</c:otherwise>
</c:choose>
