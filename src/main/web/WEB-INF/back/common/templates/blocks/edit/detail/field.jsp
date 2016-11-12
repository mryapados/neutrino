<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="finalMaxElement" value="3" />

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
		<form:textarea class="form-control" rows="2" path="${finalField.name}"/>
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
		<a class="linked" href="<c:url value='/bo/view?type=${finalObject.objectType}&id=${finalObject.id}' />"><c:out value="${finalObject.name}"/></a>
	</c:when>
	<c:when test="${finalFieldType eq 'TOBJECT' || finalFieldType eq 'NTOBJECT'}">
		<c:out value="${finalObject.name}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'OBJECT'}">
		<c:choose>
			<c:when test="${finalObject.objectType eq 'Lang'}">
				<span class="lang-sm lang-lbl" lang="${finalObject.code}"></span>
			</c:when>
			<c:otherwise>
				<a class="linked" href="<c:url value='/bo/view?type=${finalObject.objectType}&id=${finalObject.id}' />"><c:out value="object"/></a>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:when test="${finalFieldType eq 'COLLECTION'}">
		<c:set var="collection" value="${finalObject}" />
		<c:set var="size" value="${fn:length(collection)}" />
		<c:if test="${size > 0}">
			<ul class="linked">
				<c:set var="max" value="${size}" />
				
				<c:if test="${max > finalMaxElement}">
					<c:set var="max" value="${finalMaxElement}" />
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
				<c:if test="${size > finalMaxElement}">
					<li>
						<strong><a href="#"><c:out value="${size - finalMaxElement}"/> autres résultats...</a></strong>
					</li>
				</c:if>
			</ul>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:out value="${finalObject}"/>
	</c:otherwise>
</c:choose>
