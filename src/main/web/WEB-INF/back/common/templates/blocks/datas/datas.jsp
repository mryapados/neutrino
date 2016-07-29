<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="java.util.SortedSet" %>
<%@ page import="java.util.TreeSet" %>

<my:init test="${!initialized}"/>

<c:set var="pNumber" value="${datas.objectDatas.number}"/>
<c:set var="pNumberOfElements" value="${datas.objectDatas.numberOfElements}"/>
<c:set var="pSize" value="${datas.objectDatas.size}"/>
<c:set var="pSort" value="${datas.objectDatas.sort}"/>
<c:set var="pTotalElements" value="${datas.objectDatas.totalElements}"/>
<c:set var="pTotalPages" value="${datas.objectDatas.totalPages}"/>

<c:set var="pBegin" value="${pNumber * pSize}"/>
<c:set var="pEnd" value="${pBegin + pNumberOfElements}"/>

<c:set var="finalNMaxPages" value="6" />

<h1>
	<spring:message code="bo.list" text="${objectType}" />
	<spring:message code="bo.${objectType}.entity.name" text="${objectType}" />
</h1>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">	
			Showing <span class="label label-info">${pBegin}</span> to <span class="label label-info">${pEnd}</span> of <span class="label label-info">${pTotalElements}</span> entries
		</div>
		



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
					<c:forEach var="object" items="${datas.objectDatas.content}" varStatus="status">
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
						

						<c:set var="first" value="1" />
						<c:set var="last" value="${pTotalPages}" />
						<c:set var="max" value="${finalNMaxPages}" />
						<c:set var="active" value="${pNumber}" />
					
						<li><a href="#">${first}</a></li>

						<%
						
						
							int active = Integer.parseInt(pageContext.getAttribute("active").toString());
							int first = Integer.parseInt(pageContext.getAttribute("first").toString());
							int last = Integer.parseInt(pageContext.getAttribute("last").toString());
							int max = Integer.parseInt(pageContext.getAttribute("max").toString());
							int count = max;
							
							System.out.println("/////////////////////////////////////////////////////////////");
							System.out.println(active);
							System.out.println(first);
							System.out.println(last);
							System.out.println(count);
							System.out.println("/////////////////////////////////////////////////////////////");
							
							
							SortedSet<Integer> r = new TreeSet<>(); 
							r.add(active);
							
							int p = 0;
							int sens = -1;
							if (active >= last / 2){
								sens = 1;
							}
							for (int m = 1; m <= max; m++){
								
								if (count == 0) break;
								//endroit
								p = active + (m * sens);
								if (p < last && p > first){
									count--;
									r.add(p);
								}
								
								if (count == 0) break;
								//envers
								p = active - (m * sens);
								if (p < last && p > first){
									count--;
									r.add(p);
								}
								
								
							}
							
							pageContext.setAttribute("resultSet", r);
						%>
					
					
					
					
						<c:forEach var="page" items="${resultSet}" varStatus="status">
							<c:set var="classActive" value="" />
							<c:if test="${page eq active}">
								<c:set var="classActive" value=" class=\"active\"" />
							</c:if>
							<li${classActive}><a href="#">${page}</a></li>
						</c:forEach>
						
			
					
					
					
					
					
						<li><a href="#">${last}</a></li>
					
					
					
					
<!-- 						<li class="paginate_button previous"><a href="#" >Previous</a></li> -->
						
<!-- 						<li class="paginate_button active"><a href="#">6</a></li> -->
						<li class="paginate_button next disabled" id="example_next"><a href="#">Next</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
</div>

${datas.objectDatas.number}<br/>
${datas.objectDatas.numberOfElements}<br/>
${datas.objectDatas.size}<br/>
${datas.objectDatas.sort}<br/>
${datas.objectDatas.totalElements}<br/>
${datas.objectDatas.totalPages}<br/>

<%-- 		<c:forEach var="field" items="${datas.fields}" varStatus="status"> --%>
<%-- 			<p>Field = ${field.name} : ${field.type} - ${field.ofType} - ${field.inList}</p> --%>
<%-- 		</c:forEach> --%>
		
<%-- 		<c:forEach var="object" items="${datas.datas}" varStatus="status"> --%>
<%-- 			<c:forEach var="field" items="${object}" varStatus="status"> --%>
<%-- 				<p>key = ${field.key} - value = ${field.value}</p> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:forEach> --%>
