<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<c:forEach items="${categories}" var="category" varStatus="status">
	<my:bind var="categoryName" type="Category" beanId="${category.id}" field="name" />
	<c:set var="activePage" value="${category}" scope="request"/>
	<div id="${categoryName}" class="${status.index % 2 == 0 ? 'bg-color' : ''}">
		<my:block position="resume_main" />
	</div>
</c:forEach>
