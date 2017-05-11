<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<my:cache>
	<div class="profile-logo">
	    <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/profile.jpg'/>" alt="">
	</div>
	<div class="profile-info">
	    <h1>${resumeFirstName} ${resumeLastName}</h1>
	    <h4>${resumeFunction}</h4>
	    <address>
	        <p>${resumeAddress}<br> Phone: ${resumePhone} <br> <a href="#">Email: ${resumeEmail}</a></p>
	    </address>
	</div><!-- profile info -->
</my:cache>