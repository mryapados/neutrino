<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<h1>${finalFieldType}</h1>

<c:choose>
	<c:when test="${finalFieldType eq 'TOBJECT' || finalFieldType eq 'NTOBJECT'}">
		<c:out value="${finalObject.name}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'TOBJECT' || finalFieldType eq 'NTOBJECT'}">
		<c:out value="${finalObject.name}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'COLLECTION'}">
		<c:forEach var="objectCollection" items="${finalObject}" varStatus="status">
			<c:set var="finalObject" value="${objectCollection}" scope="request" />
			<c:set var="finalField" value="${finalField}" scope="request" />
			<c:set var="finalFieldType" value="${finalField.ofType}" scope="request" />
		
			<jsp:include page="field.jsp" />
		
			<c:remove var="finalObject"/>
			<c:remove var="finalField"/>
			<c:remove var="finalFieldType"/>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:out value="${finalObject}"/>
	</c:otherwise>
</c:choose>
