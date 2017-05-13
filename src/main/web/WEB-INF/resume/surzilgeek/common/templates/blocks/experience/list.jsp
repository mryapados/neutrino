<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>
            
<s:message var="pattern" code="date.locale.experience.format " text="MMMM, yyyy"/>
<s:message var="present" code="experience.present " text="Present"/>
<my:cache>
	<c:choose>
		<c:when test="${not empty experiences}">
			<c:forEach items="${experiences}" var="experience" varStatus="status">
				<my:bind var="experienceTitle" type="Experience" beanId="${experience.id}" field="title" />
				<my:bind var="experienceChapo" type="Experience" beanId="${experience.id}" field="chapo" escapeXml="false" />
				<my:bind var="experienceCompanyName" type="Experience" beanId="${experience.id}" field="companyName" />
				<my:bind var="experiencePicture" type="Experience" beanId="${experience.id}" field="picture" />
			
				<c:set var="dtStart" value="${experience.start}" />
				<fmt:formatDate var="formatedStartDate" pattern="${pattern}" value="${dtStart}" />
				
				<c:set var="dtEnd" value="<%= new java.util.Date() %>" />
				<c:set var="formatedEndDate" value="${present}" />
				<c:if test="${not empty experience.end}">
					<c:set var="dtEnd" value="${experience.end}" />
					<fmt:formatDate var="formatedEndDate" pattern="${pattern}" value="${dtEnd}" />
				</c:if>
			
				<c:url var="pictureUrl" value="${experience.picture}" scope="request"/>
			
			    <div class="exprience">
			        <c:if test="${not empty pictureUrl}">
						<div class="exprience-image">
						    <img class="img-responsive" src="${pictureUrl}" alt="Company name : ${experienceCompanyName}">
						</div>
			        </c:if>
					<div class="exprience-info">
					    <h3>${experienceTitle}</h3>
					    <h5>${formatedStartDate} - ${formatedEndDate}</h5>
					    <div>${experienceChapo}</div>
					</div>                            
			    </div>
			    <c:if test="${!status.last}"><hr></c:if>
			</c:forEach>  
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
			<div class="exprience">
			     <div class="exprience-image">
			         <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/1.png'/>" alt="Image">
			    </div>
			    <div class="exprience-info">
			        <h3>Senior Graphic Designer</h3>
			        <h5>July, 2012 - Present (4 years)</h5>
			        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			    </div>                            
			</div>
			<hr>
			<div class="exprience">
			    <div class="exprience-image">
			        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/2.png'/>" alt="Image">
			    </div>
			    <div class="exprience-info">
			        <h3>Former Graphic Designer</h3>
			        <h5>March, 2011 - June, 2012 (1 year, 3 Month)</h5>
			        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			    </div>                            
			</div>
			<hr>
			<div class="exprience">
			    <div class="exprience-image">
			        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/3.png'/>" alt="Image">
			    </div>
			    <div class="exprience-info">
			        <h3>Head of Design</h3>
			        <h5>March, 2005 - March, 2011 (6 years)</h5>
			        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			    </div>                            
			</div>
			<hr>
			<div class="exprience">
			    <div class="exprience-image">
			        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/4.png'/>" alt="Image">
			    </div>
			    <div class="exprience-info">
			        <h3>Graphic Designer</h3>
			        <h5>January, 2004 - March, 2005 (1 Year, 2 Months)</h5>
			        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			    </div>                            
			</div>
			<hr>
			<div class="exprience">
			    <div class="exprience-image">
			        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/5.png'/>" alt="Image">
			    </div>
			    <div class="exprience-info">
			        <h3>Graphic Designer (intern)</h3>
			        <h5>January, 2002 - August, 2004 (2 years, 7 months)</h5>
			        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
			    </div>                            
			</div>
		</c:when>
	</c:choose>		
</my:cache>	
	
