<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:choose>
	<c:when test="${finalFieldType eq 'DATETIME'}">
		<fmt:formatDate value="${finalObject}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'TOBJECT' || finalFieldType eq 'NTOBJECT'}">
		<c:out value="${finalObject.name}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'TOBJECT' || finalFieldType eq 'NTOBJECT'}">
		<c:out value="${finalObject.name}"/>
	</c:when>
	<c:when test="${finalFieldType eq 'OBJECT'}">
		<c:if test="${finalObject.objectType eq 'Lang'}">
			<span class="lang-sm lang-lbl" lang="${finalObject.code}"></span>
		</c:if>
	</c:when>
	<c:when test="${finalFieldType eq 'COLLECTION'}">
		<ul>
			<c:forEach var="objectCollection" items="${finalObject}" varStatus="status">
				<c:set var="finalObject" value="${objectCollection}" scope="request" />
				<c:set var="finalFieldType" value="${finalField.ofType}" scope="request" />
				
				<li>
					<jsp:include page="field.jsp" />
				</li>
				
				<c:remove var="finalObject"/>
				<c:remove var="finalFieldType"/>
			</c:forEach>
		</ul>

	</c:when>
	<c:otherwise>
		<c:out value="${finalObject}"/>
	</c:otherwise>
</c:choose>
