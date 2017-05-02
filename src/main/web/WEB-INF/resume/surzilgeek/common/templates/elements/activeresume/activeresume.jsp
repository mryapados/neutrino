<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${not empty activeResume}">
		<my:bind var="resumeFirstName" type="Resume" beanId="${activeResume.id}" field="firstName" scope="request" />
		<my:bind var="resumeLastName" type="Resume" beanId="${activeResume.id}" field="lastName" scope="request" />
		<my:bind var="resumeFunction" type="Resume" beanId="${activeResume.id}" field="function" scope="request" />
		<my:bind var="resumeDescription" type="Resume" beanId="${activeResume.id}" field="description" scope="request" />
		<my:bind var="resumeEmail" type="Resume" beanId="${activeResume.id}" field="email" scope="request" />
		<my:bind var="resumePhone" type="Resume" beanId="${activeResume.id}" field="phone" scope="request" />
		<my:bind var="resumeDateOfBirth" type="Resume" beanId="${activeResume.id}" field="dateOfBirth" scope="request" />
		<my:bind var="resumeAddress" type="Resume" beanId="${activeResume.id}" field="address" scope="request" />
		<my:bind var="resumeBanner" type="Resume" beanId="${activeResume.id}" field="banner" />
		<c:url var="resumeBannerUrl" value="${resumeBanner}" scope="request"/>
	</c:when>
	<c:otherwise>
		<c:set var="resumeFirstName" value="Surzil" scope="request" />
		<c:set var="resumeLastName" value="Geek" scope="request" />
		<c:set var="resumeFunction" value="Web Developer" scope="request" />
		<c:set var="resumeDescription" value="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." scope="request" />
		<c:set var="resumeEmail" value="itsme@surzilgeek.com" scope="request" />
		<c:set var="resumePhone" value="(123)-456-78910" scope="request" />
		<c:set var="resumeDateOfBirth" value="<%= new java.util.Date() %>" scope="request" />
		<c:set var="resumeAddress" value="121 King Street, Melbourne Victoria, 1200 USA" scope="request" />
		<c:set var="resumeBanner" value="/resources/src/resume/surzilgeek/images/bg/banner.jpg"/>
		<c:url var="resumeBannerUrl" value="${resumeBanner}" scope="request"/>
	</c:otherwise>
</c:choose>