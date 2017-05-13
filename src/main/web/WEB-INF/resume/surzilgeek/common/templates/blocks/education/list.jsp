<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<my:cache>
	<c:choose>
		<c:when test="${not empty activeResume}">
			<c:forEach items="${educations}" var="education" varStatus="status">
				<my:bind var="educationTitle" type="Education" beanId="${education.id}" field="title" />
				<my:bind var="educationSchoolName" type="Education" beanId="${education.id}" field="schoolName" />
				<my:bind var="educationChapo" type="Education" beanId="${education.id}" field="chapo" escapeXml="false" />
				<my:bind var="educationPicture" type="Education" beanId="${education.id}" field="picture" />
				<my:bind var="educationYear" type="Education" beanId="${education.id}" field="year" />
			
				<c:url var="pictureUrl" value="${education.picture}" scope="request"/>
			
			    <div class="education">
			        <c:if test="${not empty pictureUrl}">
						<img class="img-responsive" src="${pictureUrl}" alt="">
			        </c:if>
					<div class="education-info">
					    <h3>${educationYear} - <c:out value="${educationTitle}"/></h3>
					    <h5>${educationSchoolName}</h5>
					    <div>${educationChapo}</div>
					</div>                            
			    </div>
			    <c:if test="${!status.last}"><hr></c:if>
			</c:forEach>
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
			<div class="education-info">
			    <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/education/1.png'/>" alt="Image">
			    <h3>Montana State University</h3>
			    <h5>Bachalor of Arts</h5>
			    <h6>1999 - 2003</h6>
			    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			</div>
			<hr>
			<div class="education-info">
			    <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/education/2.png'/>" alt="Image">
			    <h3>Montana State University</h3>
			    <h5>University of Bristol</h5>
			    <h6>1999 - 2003</h6>
			    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			</div>
			<hr>
			<div class="education-info">
			    <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/education/3.png'/>" alt="Image">
			    <h3>Cincinnati Christian University</h3>
			    <h5>Bachalor of Arts</h5>
			    <h6>1999 - 2003</h6>
			    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			</div>
		</c:when>
	</c:choose>
</my:cache>