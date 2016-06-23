<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>


<my:init test="${!initialized}"/>

<my:cache>
	<c:set var="test" value="test" />
	<h1><c:out value="${test}" /></h1>
	
	<my:discrimin name="socialnetwork" scope="page">
		<h1>Je suis affiché</h1>
	</my:discrimin>
	<my:discrimin name="socialnetwork" scope="page">
		<h1>Je ne suis pas affiché</h1>
	</my:discrimin>
	
</my:cache>

<h1>AH AH AH AH OH </h1>

<div class="socialnetwork">
	<img src="<c:url value='/image/front/social/facebook.png'/>" />
	<img src="<c:url value='/image/front/social/googleplus.png'/>" />
	<img src="<c:url value='/image/front/social/twitter.png'/>" />
	<img src="<c:url value='/image/front/social/linkedin.png'/>" />
</div>