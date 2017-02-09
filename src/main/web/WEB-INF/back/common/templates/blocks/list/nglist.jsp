<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<my:init test="${!initialized}"/>

<c:set var="displayType" value="dynamic" scope="request" />
<jsp:include page="detail/${displayType}/init.jsp" />
<div class="list-object">
	<p>values = {{values}}</p>
	Showing <span class="label label-info">${pBegin + 1}</span> to <span class="label label-info">${pEnd}</span> of <span class="label label-info">${pTotalElements}</span> entries

	<div>
		<jsp:include page="detail/${displayType}/toolbar.jsp">
			<jsp:param value="top" name="position"/>
		</jsp:include>
	</div>
	
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover" role="grid">
			<thead>
				<tr>
					<th><input type="checkbox" id="select_all"></th>
					<th><s:message code="bo.field.action" text="Action" /></th>
					<c:forEach var="field" items="${fields}" varStatus="status">
						<c:if test="${field.inList}">
							<s:message var="defaultMessage" code="bo.field.${field.name}" text="${field.name}" />
							<th><s:message code="bo.${objectType}.field.${field.name}" text="${defaultMessage}" /></th>
						</c:if>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="object" items="${datas}" varStatus="status">
					<tr>
						<td>
							<input type="checkbox" name="id" value="${object.id}" ng-checked="true"/>
						</td>
						<td class="text-center">
							<c:url var="url" value="/bo/edit/" scope="request">
								<c:param name="type" value="${objectType}"/>
								<c:param name="id" value="${object.id}"/>
							</c:url>
							<a href="${url}" title="edit"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>
							&nbsp
							<c:url var="url" value="/bo/view/" scope="request">
								<c:param name="type" value="${param.type}"/>
								<c:param name="id" value="${object.id}"/>
							</c:url>
							<a href="${url}" title="see"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
						</td>
						<c:forEach var="field" items="${fields}" varStatus="status">
							<c:if test="${field.inList}">
								<td>
									<c:set var="finalParentObject" value="${object}" scope="request" />
									<c:set var="finalObject" value="${object[field.name]}" scope="request" />
									<c:set var="finalField" value="${field}" scope="request" />
									<c:set var="finalFieldType" value="${finalField.type}" scope="request" />
									
									<jsp:include page="detail/field.jsp" />
									
									<c:remove var="finalParentObject"/>
									<c:remove var="finalObject"/>
									<c:remove var="finalField"/>
									<c:remove var="finalFieldType"/>
								</td>
							</c:if>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	
	
	<div>
		<jsp:include page="detail/${displayType}/toolbar.jsp">
			<jsp:param value="bottom" name="position"/>
		</jsp:include>
	</div>

		
		
	
</div>

