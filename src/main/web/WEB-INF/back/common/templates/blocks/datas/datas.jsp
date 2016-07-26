<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<h1><c:out value="${objectType}"/></h1>


<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">List</div>
		<div class="panel-body">
		
		


		
		
<%-- 		<c:forEach var="field" items="${datas.fields}" varStatus="status"> --%>
<%-- 			<p>Field = ${field.name} : ${field.type} - ${field.ofType} - ${field.inList}</p> --%>
<%-- 		</c:forEach> --%>
		
<%-- 		<c:forEach var="object" items="${datas.datas}" varStatus="status"> --%>
<%-- 			<c:forEach var="field" items="${object}" varStatus="status"> --%>
<%-- 				<p>key = ${field.key} - value = ${field.value}</p> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:forEach> --%>
		
		
		
		
		
		
		</div>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<c:forEach var="field" items="${datas.fields}" varStatus="status">
							<th>${field.name}</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="object" items="${datas.datas}" varStatus="status">
						<tr>
							<c:forEach var="field" items="${datas.fields}" varStatus="status">
								<td>
									<c:set var="finalObject" value="${object[field.name]}" scope="request" />
									<c:set var="finalField" value="${field}" scope="request" />
									<c:set var="finalFieldType" value="${finalField.type}" scope="request" />
									
									<jsp:include page="field.jsp" />

									<c:remove var="finalObject"/>
									<c:remove var="finalField"/>
									<c:remove var="finalFieldType"/>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
</div>

