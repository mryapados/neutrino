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
	                <my:block position="resume_aboutMe" />
                </article>
            </div>
        </div><!-- row -->
    </div><!-- container -->
</section><!-- about section -->         
                               
                   