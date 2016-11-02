<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>


<c:set var="position" value="${param.position}"/>
<div class="btn-toolbar" role="toolbar" style="${position eq 'top' ? ' margin-bottom:15px;' : ' margin-top:15px;'}">
	<div class="btn-group" role="group" aria-label="...">
		<div class="btn-group" role="group">
		
			<button type="submit" class="btn btn-success">
				<span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>
				<s:message code="bo.edit.button.save" text="Save" />
			</button>
			<a href="TODO CANCEL" class="btn btn-primary">
				<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
				<s:message code="bo.edit.button.cancel" text="Cancel" />
			</a>

		</div>
	</div>
</div>