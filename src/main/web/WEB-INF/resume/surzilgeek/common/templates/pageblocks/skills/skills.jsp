<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>
            
			
<div id="skill" class="skill-section bg-color section-padding">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="section-content">
                    <div class="section-title">
                        <h1><s:message code="skills.title" text="My Skills" /></h1>
                        <p>${activePage.description}</p>
                    </div>

					<my:block position="resume_skills" />
                </div>                                             
            </div>
        </div><!-- row -->
    </div><!-- container -->
</div><!-- skill section -->                         
                   