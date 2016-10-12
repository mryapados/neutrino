<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<jsp:include page="detail/init.jsp" />
<div class="view-object">
	<h1>
		<s:message code="bo.list" text="${objectType}" />
		<s:message code="bo.${objectType}.entity.name" text="${objectType}" />
	</h1>
	
	
			
			
			

<data-uib-tabset active="active">

	<c:forEach var="tab" items="${data.fields}" varStatus="status">
		<data-uib-tab index="${status.index}" heading="${not empty tab.key ? tab.key : 'data'}">
		
		
		

			<c:forEach var="group" items="${tab.value}">
				<table class="table table-striped table-bordered" role="grid">
					<thead>
						<tr>
							<th colspan="2">${not empty group.key ? group.key : 'data'}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="field" items="${group.value}">
							<tr>
								<td>
									${field.name} : ${field.type}
								</td>
								<td>
									${data.objectData}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:forEach>
		
		
		
			
		
		
		</data-uib-tab>
	</c:forEach>

</data-uib-tabset>
			
	
			
			


<%-- 		<c:forEach var="tab" items="${data.fields}"> --%>
<%-- 		    >>> tab = ${tab.key}<br/> --%>
<!-- 			>>> ---------------------------<br/> -->
<%-- 			<c:forEach var="group" items="${tab.value}"> --%>
<%-- 			   >>> >>> group = ${group.key}<br/> --%>
<!-- 			   >>> >>> ---------------------------<br/> -->

<%-- 				<c:forEach var="field" items="${group.value}"> --%>
<%-- 				   >>> >>> >>> field = ${field.name} : ${field.type}<br/> --%>
<!-- 				   >>> >>> >>> ---------------------------<br/> -->

<%-- 				</c:forEach> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:forEach> --%>
	
	
	

			
			
			
			
			
			

			
			
			
			
			
			
			
			
			

</div>