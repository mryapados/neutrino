<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<jsp:include page="detail/init.jsp" />
<div class="list-object">
	<h1>
		<s:message code="bo.list" text="${objectType}" />
		<s:message code="bo.${objectType}.entity.name" text="${objectType}" />
	</h1>
	<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-heading">	

			</div>
			<div class="panel-header">
				
			</div>
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover" role="grid">
					<thead>

					</thead>
					<tbody>
					
					</tbody>
				</table>
			</div>
			<div class="panel-footer">
				
			</div>
		</div>
	</div>
</div>