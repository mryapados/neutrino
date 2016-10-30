<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="finalMaxElement" value="3" />

<c:choose>
	<c:when test="${finalFieldType eq 'INTEGER'}">
		<input type="number" class="form-control" name="frm_${finalField.name}" value="<c:out value='${finalObject}'/>">
	</c:when>
	<c:when test="${finalFieldType eq 'VARCHAR50' || finalFieldType eq 'VARCHAR255'}">
		<textarea class="form-control" rows="2" name="frm_${finalField.name}"><c:out value='${finalObject}'/></textarea>
	</c:when>
	<c:when test="${finalFieldType eq 'TEXT'}">	
		<textarea class="form-control" rows="5" name="frm_${finalField.name}"><c:out value='${finalObject}'/></textarea>
	</c:when>
	<c:when test="${finalFieldType eq 'HTML'}">	
		<jsp:include page="wysiwyg.jsp" />
	</c:when>
	

	<c:when test="${finalFieldType eq 'DATETIME'}">
		<fmt:formatDate var="dateObj" value="${finalObject}" pattern="yyyy-MM-dd HH:mm:ss" />
		<div ng-controller="DatepickerPopupCtrl" ng-init="init('${dateObj}')">
			<p class="input-group">
				<input type="text" 
					class="form-control"
					uib-datepicker-popup="{{format}}" 
					ng-model="dt"
					is-open="popup1.opened" 
					datepicker-options="dateOptions"
					ng-required="true" 
					close-text="Close"
					alt-input-formats="altInputFormats" 
				/>
				<span class="input-group-btn">
					<button type="button" class="btn btn-default" ng-click="open1()">
						<i class="glyphicon glyphicon-calendar"></i>
					</button>
				</span>
			</p>

		</div>
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
