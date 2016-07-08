<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:forEach var="field" items="${datas.fields}" varStatus="status">
	<p>Field = ${field.name} : ${field.type} - ${field.ofType} - ${field.inList}</p>
</c:forEach>

<c:forEach var="object" items="${datas.datas}" varStatus="status">
	<c:forEach var="field" items="${object}" varStatus="status">
		<p>key = ${field.key} - value = ${field.value}</p>
	</c:forEach>
</c:forEach>
