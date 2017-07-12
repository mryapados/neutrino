<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>


<c:choose>
	<c:when test="${activePage.objectType eq 'Category' && empty activePage.parent}">
		<my:url var="canonical" value="/" />
	</c:when>
	<c:when test="${activePage.objectType eq 'Article'}">
		<my:url var="canonical" value="/todo/article/${activePage.name}.html" />
	</c:when>
	<c:otherwise>
		<my:url var="canonical" value="/todo/${activePage.name}.html" />
	</c:otherwise>
</c:choose>


<link rel="canonical" href="${canonical}" />