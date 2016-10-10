<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div class="btn-toolbar" role="toolbar">
	<div class="btn-group" role="group" aria-label="...">
		<div class="btn-group" role="group">
			<div class="dropup">
				<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<strong><c:out value="${pSize}"/></strong> by page <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li>
						<jsp:include page="url.jsp">
							<jsp:param name="size" value="10" />
							<jsp:param name="expr" value="10" />
						</jsp:include>
					</li>
					<li>
						<jsp:include page="url.jsp">
							<jsp:param name="size" value="25" />
							<jsp:param name="expr" value="25" />
						</jsp:include>
					</li>
					<li>
						<jsp:include page="url.jsp">
							<jsp:param name="size" value="50" />
							<jsp:param name="expr" value="50" />
						</jsp:include>
					</li>
					<li>
						<jsp:include page="url.jsp">
							<jsp:param name="size" value="100" />
							<jsp:param name="expr" value="100" />
						</jsp:include>
					</li>
				</ul>
			</div>
		</div>
		<div class="btn-group" role="group">
			<div class="dropup">
				<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<s:message var="defaultMessage" code="bo.field.${pFirstSortName}" text="${pFirstSortName}" />
					<s:message var="fieldName" code="bo.${objectType}.field.${pFirstSortName}" text="${defaultMessage}" />
					Sort by <strong><c:out value="${fieldName}"/></strong> <span class="caret"></span>
				</button>
				
				<ul class="dropdown-menu">
					<c:forEach var="field" items="${datas.fields}" varStatus="status">
						<c:if test="${field.inList && field.type ne 'COLLECTION'}">
							<s:message var="defaultMessage" code="bo.field.${field.name}" text="${field.name}" />
							<s:message var="fieldName" code="bo.${objectType}.field.${field.name}" text="${defaultMessage}" />
							<li>
								<jsp:include page="url.jsp">
									<jsp:param name="sort" value="${field.name},${pFirstSortDirection}" />
									<jsp:param name="expr" value="${fieldName}" />
								</jsp:include>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	
		<jsp:include page="url.jsp">
			<jsp:param name="sort" value="${pFirstSortName},ASC" />
			<jsp:param name="expr" value="ASC" />
			<jsp:param name="css" value="btn btn-default${pFirstSortDirection eq 'ASC' ? ' active' : ''}" />
			<jsp:param name="role" value="button" />
		</jsp:include>
		<jsp:include page="url.jsp">
			<jsp:param name="sort" value="${pFirstSortName},DESC" />
			<jsp:param name="expr" value="DESC" />
			<jsp:param name="css" value="btn btn-default${pFirstSortDirection eq 'DESC' ? ' active' : ''}" />
			<jsp:param name="role" value="button" />
		</jsp:include>

	</div>


	<div class="btn-group" role="group" aria-label="...">
		<div class="btn-group" role="group">
			<button id="delete_button" type="submit" class="btn btn-danger">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Remove
			</button>
			
			<c:url var="url" value="./new/" scope="request">
				<c:param name="type" value="${objectType}"/>
			</c:url>
			<a href="${url}" role="button" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add
			</a>
		</div>
	</div>

    <div class="btn-group pull-right">
		<jsp:include page="pagination.jsp" />
    </div>
</div>