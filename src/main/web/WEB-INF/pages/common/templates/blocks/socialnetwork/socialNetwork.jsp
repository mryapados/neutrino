<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>


<my:init test="${!initialized}"/>


<my:discrimin name="socialnetwork">
	<my:script src="test script src"></my:script>
	<my:script>
		<c:set var="testset" value="1" />
		<c:out value="${testset}" />
	</my:script>
</my:discrimin>


<my:discrimin name="socialnetwork" scope="session">
	<h1>JE SUIS PAS LA</h1>
</my:discrimin>
<my:discrimin name="socialnetwork" scope="session">
	<h1>JE SUIS PAS LA 2</h1>
</my:discrimin>




<div class="socialnetwork">
	<img src="<c:url value='/image/front/social/facebook.png'/>" />
	<img src="<c:url value='/image/front/social/googleplus.png'/>" />
	<img src="<c:url value='/image/front/social/twitter.png'/>" />
	<img src="<c:url value='/image/front/social/linkedin.png'/>" />
</div>