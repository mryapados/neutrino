<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>


<my:init test="${!initialized}"/>


<my:script src="test script src">ccccc</my:script>
<my:script src="test src">ddddd</my:script>
<my:script src="test src eeee">
	eeeeeee
</my:script>


<div class="socialnetwork">
	<img src="<c:url value='/image/front/social/facebook.png'/>" />
	<img src="<c:url value='/image/front/social/googleplus.png'/>" />
	<img src="<c:url value='/image/front/social/twitter.png'/>" />
	<img src="<c:url value='/image/front/social/linkedin.png'/>" />
</div>