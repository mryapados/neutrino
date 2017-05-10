<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<my:cache>
	<c:choose>
		<c:when test="${not empty educations}">

			<div class="educational-background">
	            <div class="title">
	                <div class="icons">
	                    <i class="fa fa-graduation-cap" aria-hidden="true"></i>
	                </div>                                    
	                <h3>Educational Background</h3>                                    
	            </div>
				<div class="sub-content">
					<c:forEach items="${educations}" var="education" varStatus="status">
						<my:bind var="educationTitle" type="Education" beanId="${education.id}" field="title" />
						<my:bind var="educationSchoolName" type="Education" beanId="${education.id}" field="schoolName" />
						<my:bind var="educationChapo" type="Education" beanId="${education.id}" field="chapo" />
						<my:bind var="educationYear" type="Education" beanId="${education.id}" field="year" />
						
                        <div class="education">
						    <h4>${educationYear} - <c:out value="${educationTitle}"/> @ <c:out value="${educationSchoolName}"/></h4>
                            <p><c:out value="${educationChapo}" escapeXml="false" /></p>
                        </div>
						<c:if test="${!status.last}"><hr></c:if>

					</c:forEach>
                </div>
            </div>
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
            <div class="educational-background">
                <div class="title">
                    <div class="icons">
                        <i class="fa fa-graduation-cap" aria-hidden="true"></i>
                    </div>                                    
                    <h3>Educational Background</h3>                                    
                </div>
                <div class="sub-content">
                    <div class="education">
                        <h5>Masters of Arts @ Montana Satet University</h5>
                        <p>1999 - 2001</p>
                    </div>
                    <hr>
                    <div class="education">
                        <h5>Bachalor of Arts @ Universty of Bristol</h5>
                        <p>1995 - 1999</p>
                    </div>
                    <hr>
                    <div class="education">
                        <h5>Diploma in Graphics Design @ Cincinnati Christian University</h5>
                        <p>1993 - 1995</p>
                    </div>
                </div>
            </div>
		</c:when>
	</c:choose>
</my:cache>