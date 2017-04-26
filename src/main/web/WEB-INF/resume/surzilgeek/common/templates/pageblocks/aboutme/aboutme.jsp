<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<my:init test="${!initialized}"/>

<s:message var="pattern" code="date.locale.format" text="MM-dd-yyyy" />
<fmt:formatDate var="formatedDate" pattern="${pattern}" value="${resumeDateOfBirth}" />
	
<section id="about" class="about-section section-padding">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <header class="section-title">
                    <h2>
                    	${resumeFirstName} ${resumeLastName}
                    	<span>${resumeFunction}</span>
                    </h2>
                </header>
                <article>
	                <div class="about-info">
	                    <p>${resumeDescription}</p>
	                    <div class="signature">
	                        <p class="big">${resumeFirstName} ${resumeLastName}</p>
	                    </div>
	                </div>
	                <address>
	                    <p><span><s:message code="resume.nickname" text="Nick Name:" /></span> ${resumeFirstName} ${resumeLastName}</p>
	                    <p><span><s:message code="resume.email" text="Email:" /></span> ${resumeEmail}</p>
	                    <p><span><s:message code="resume.phone" text="Phone:" /></span> ${resumePhone}</p>
	                    <p><span><s:message code="resume.dateofbirth" text="Date of Birth:" /></span> ${formatedDate}</p>
	                    <p><span><s:message code="resume.address" text="Address:" /></span> ${resumeAddress}</p>
	                </address>
	                <my:block position="resume_aboutMe" />
                </article>
            </div>
        </div><!-- row -->
    </div><!-- container -->
</section><!-- about section -->         
                        
			
					
                                                            
                   