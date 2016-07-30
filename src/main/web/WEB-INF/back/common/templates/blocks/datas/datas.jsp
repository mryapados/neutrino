<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
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

	
<c:url var="urlFull" value="" >
	<c:param name="type" value="${param.type}" />
	<c:if test="${not empty param.lang}">
		<c:param name="lang" value="${param.lang}" />
	</c:if>
	<c:if test="${not empty param.page}">
		<c:param name="page" value="${param.page}" />
	</c:if>
	<c:if test="${not empty param.size}">
		<c:param name="size" value="${param.size}" />
	</c:if>
	<c:if test="${not empty param.sort}">
		<c:param name="sort" value="${param.sort}" />
	</c:if>
</c:url>
<h6><c:out value="${urlFull}" /></h6>

<c:url var="urlNoPage" value="" >
	<c:param name="type" value="${param.type}" />
	<c:if test="${not empty param.lang}">
		<c:param name="lang" value="${param.lang}" />
	</c:if>
	<c:if test="${not empty param.size}">
		<c:param name="size" value="${param.size}" />
	</c:if>
	<c:if test="${not empty param.sort}">
		<c:param name="sort" value="${param.sort}" />
	</c:if>
</c:url>
<h6><c:out value="${urlNoPage}" /></h6>

<c:url var="urlNoSize" value="" >
	<c:param name="type" value="${param.type}" />
	<c:if test="${not empty param.lang}">
		<c:param name="lang" value="${param.lang}" />
	</c:if>
	<c:if test="${not empty param.page}">
		<c:param name="page" value="${param.page}" />
	</c:if>
	<c:if test="${not empty param.sort}">
		<c:param name="sort" value="${param.sort}" />
	</c:if>
</c:url>
<h6><c:out value="${urlNoSize}" /></h6>

<c:url var="urlNoSort" value="" >
	<c:param name="type" value="${param.type}" />
	<c:if test="${not empty param.lang}">
		<c:param name="lang" value="${param.lang}" />
	</c:if>
	<c:if test="${not empty param.page}">
		<c:param name="page" value="${param.page}" />
	</c:if>
	<c:if test="${not empty param.size}">
		<c:param name="size" value="${param.size}" />
	</c:if>
</c:url>
<h6><c:out value="${urlNoSort}" /></h6>

<h1>
	<s:message code="bo.list" text="${objectType}" />
	<s:message code="bo.${objectType}.entity.name" text="${objectType}" />
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
								<s:message var="defaultMessage" code="bo.field.${field.name}" text="${field.name}" />
								<th><s:message code="bo.${objectType}.field.${field.name}" text="${defaultMessage}" /></th>
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
				<div class="col-md-12">
					<ul class="pagination pull-right">
						<c:set var="first" value="1" />
						<c:set var="last" value="${pTotalPages}" />
						<c:set var="max" value="${finalNMaxPages}" />
						<c:set var="active" value="${pNumber + 1 >= last ? last : pNumber + 1}" />
						<%
							int active = Integer.parseInt(pageContext.getAttribute("active").toString());
							int first = Integer.parseInt(pageContext.getAttribute("first").toString());
							int last = Integer.parseInt(pageContext.getAttribute("last").toString());
							int max = Integer.parseInt(pageContext.getAttribute("max").toString());
							int count = max;

							SortedSet<Integer> r = new TreeSet<>();
							r.add(first);
							r.add(active);
							r.add(last);
							
							int p = 0;
							int sens = -1;
							if (active >= last / 2){
								sens = 1;
							}
							for (int m = 1; m <= max; m++){
								for (int i = 0; i <= 1; i++){
									if (count == 0) break;
									p = active + (m * sens);
									if (p < last && p > first){
										count--;
										r.add(p);
									}
									sens = -sens;
								}
							}
							pageContext.setAttribute("resultSet", r);
						%>
						

								
						
						<li class="paginate_button previous${active eq 1 ? ' disabled' : ''}">
							<c:url var="url" value="" >
								<c:param name="type" value="${param.type}" />
								<c:if test="${not empty param.lang}">
									<c:param name="lang" value="${param.lang}" />
								</c:if>
								<c:param name="page" value="${active - 2}" />
								<c:if test="${not empty param.size}">
									<c:param name="size" value="${param.size}" />
								</c:if>
								<c:if test="${not empty param.sort}">
									<c:param name="sort" value="${param.sort}" />
								</c:if>
							</c:url>
							<a href="${url}">Previous</a>
						</li>
						<c:forEach var="page" items="${resultSet}" varStatus="status">
							<c:set var="classActive" value="" />
							<c:if test="${page - previous > 1}">
								<li class="disabled"><a href="">...</a></li>
							</c:if>
							<li${page eq active ? ' class=\"active\"' : ''}>
								<c:url var="url" value="" >
									<c:param name="type" value="${param.type}" />
									<c:if test="${not empty param.lang}">
										<c:param name="lang" value="${param.lang}" />
									</c:if>
									<c:param name="page" value="${page - 1}" />
									<c:if test="${not empty param.size}">
										<c:param name="size" value="${param.size}" />
									</c:if>
									<c:if test="${not empty param.sort}">
										<c:param name="sort" value="${param.sort}" />
									</c:if>
								</c:url>
								<a href="${url}">${page}</a>
							</li>
							<c:set var="previous" value="${page}" />
						</c:forEach>
						<li class="paginate_button next${active eq last ? ' disabled' : ''}">
							<c:url var="url" value="" >
								<c:param name="type" value="${param.type}" />
								<c:if test="${not empty param.lang}">
									<c:param name="lang" value="${param.lang}" />
								</c:if>
								<c:param name="page" value="${active}" />
								<c:if test="${not empty param.size}">
									<c:param name="size" value="${param.size}" />
								</c:if>
								<c:if test="${not empty param.sort}">
									<c:param name="sort" value="${param.sort}" />
								</c:if>
							</c:url>
							<a href="${url}">Next</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
