<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>
            
<my:cache>
	<c:choose>
		<c:when test="${not empty experiences}">

	        <div class="work-history">
	            <div class="title">
	                <div class="icons">
	                    <i class="fa fa-briefcase" aria-hidden="true"></i>
	                </div>                                    
	                <h3>Work History</h3>
	            </div>
	            <div class="sub-content">
		            <s:message var="pattern" code="date.locale.experience.format " text="MMMM, yyyy"/>
					<s:message var="present" code="experience.present " text="Present"/>
					<c:forEach items="${experiences}" var="experience" varStatus="status">
						<my:bind var="experienceTitle" type="Experience" beanId="${experience.id}" field="title" />
						<my:bind var="experienceCompanyName" type="Experience" beanId="${experience.id}" field="companyName" />
						<my:bind var="experienceChapo" type="Experience" beanId="${experience.id}" field="chapo" />
					
						<c:set var="dtStart" value="${experience.start}" />
						<fmt:formatDate var="formatedStartDate" pattern="${pattern}" value="${dtStart}" />
						
						<c:set var="dtEnd" value="<%= new java.util.Date() %>" />
						<c:set var="formatedEndDate" value="${present}" />
						<c:if test="${not empty experience.end}">
							<c:set var="dtEnd" value="${experience.end}" />
							<fmt:formatDate var="formatedEndDate" pattern="${pattern}" value="${dtEnd}" />
						</c:if>
					
		                <div class="history">
		                    <h4><c:out value="${experienceTitle}"/> @ <c:out value="${experienceCompanyName}"/></h4>
		                    <h5>${formatedStartDate} - ${formatedEndDate}</h5>
						    <div>
						    	<c:out value="${experienceChapo}" escapeXml="false" />
						    </div>
		                </div>
						<c:if test="${!status.last}"><hr></c:if>
					</c:forEach>
	            </div>
            </div><!-- work history -->
            <hr> 
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
            <div class="work-history">
                <div class="title">
                    <div class="icons">
                        <i class="fa fa-briefcase" aria-hidden="true"></i>
                    </div>                                    
                    <h3>Work History</h3>
                </div>
                <div class="sub-content">
                    <div class="history">
                        <h5>Senior Graphic Designer @ Buildomo</h5>
                        <h6>2012 - Present</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    </div>
                    <hr>
                    <div class="history">
                        <h5>Former Graphic Designer @ Ideame</h5>
                        <h6>2011 - 2012</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    </div>
                    <hr>
                    <div class="history">
                        <h5>Head of Design @ Titan Compnay</h5>
                        <h6>2005 - 2011</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    </div>
                    <hr>
                    <div class="history">
                        <h5>Graphic Designer @ Precision</h5>
                        <h6>2004 - 2005</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    </div>
                    <hr>
                    <div class="history">
                        <h5>Graphic Designer (Intern) @ Costa Rica Fruit Compnay</h5>
                        <h6>2002 - 2004</h6>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                    </div>
                </div>
            </div><!-- work history -->
            <hr> 
		</c:when>
	</c:choose>		
</my:cache>	
	
