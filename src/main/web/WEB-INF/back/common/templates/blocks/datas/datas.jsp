<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<h1>
	<spring:message code="bo.list" text="${objectType}" />
	<spring:message code="bo.${objectType}.entity.name" text="${objectType}" />
</h1>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">	
			Showing <span class="label label-info">5</span> to <span class="label label-info">12</span> of <span class="label label-info">25</span> entries
		</div>
		
<%-- 		<c:forEach var="field" items="${datas.fields}" varStatus="status"> --%>
<%-- 			<p>Field = ${field.name} : ${field.type} - ${field.ofType} - ${field.inList}</p> --%>
<%-- 		</c:forEach> --%>
		
<%-- 		<c:forEach var="object" items="${datas.datas}" varStatus="status"> --%>
<%-- 			<c:forEach var="field" items="${object}" varStatus="status"> --%>
<%-- 				<p>key = ${field.key} - value = ${field.value}</p> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:forEach> --%>

		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover" role="grid">
				<thead>
					<tr>
						<c:forEach var="field" items="${datas.fields}" varStatus="status">
							<c:if test="${field.inList}">
								<spring:message var="defaultMessage" code="bo.field.${field.name}" text="${field.name}" />
								<th><spring:message code="bo.${objectType}.field.${field.name}" text="${defaultMessage}" /></th>
							</c:if>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="object" items="${datas.datas}" varStatus="status">
						<tr>
							<c:forEach var="field" items="${datas.fields}" varStatus="status">
								<c:if test="${field.inList}">
									<td>
										<c:set var="finalObject" value="${object[field.name]}" scope="request" />
										<c:set var="finalField" value="${field}" scope="request" />
										<c:set var="finalFieldType" value="${finalField.type}" scope="request" />
										
										<jsp:include page="field.jsp" />
	
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

		<div class="panel-footer">
			<div class="row">
				<div class="col-md-6">

				</div>
				<div class="col-md-6">

					<ul class="pagination pull-right">
						<li class="paginate_button previous" id="example_previous"><a
							href="#" aria-controls="example" data-dt-idx="0" tabindex="0">Previous</a></li>
						<li class="paginate_button "><a href="#"
							aria-controls="example" data-dt-idx="1" tabindex="0">1</a></li>
						<li class="paginate_button "><a href="#"
							aria-controls="example" data-dt-idx="2" tabindex="0">2</a></li>
						<li class="paginate_button "><a href="#"
							aria-controls="example" data-dt-idx="3" tabindex="0">3</a></li>
						<li class="paginate_button "><a href="#"
							aria-controls="example" data-dt-idx="4" tabindex="0">4</a></li>
						<li class="paginate_button "><a href="#"
							aria-controls="example" data-dt-idx="5" tabindex="0">5</a></li>
						<li class="paginate_button active"><a href="#"
							aria-controls="example" data-dt-idx="6" tabindex="0">6</a></li>
						<li class="paginate_button next disabled" id="example_next"><a
							href="#" aria-controls="example" data-dt-idx="7" tabindex="0">Next</a></li>
					</ul>
				</div>
			</div>
		</div>




	</div>
</div>

