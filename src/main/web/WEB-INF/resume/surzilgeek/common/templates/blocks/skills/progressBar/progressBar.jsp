<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<c:if test="${not empty skills}">
	<c:set var="skillsSize" value="${skills.size()}" />
	<fmt:formatNumber var="colRight" value="${skillsSize / 2}" maxFractionDigits="0" />
	<c:set var="colLeft" value="${skillsSize - colRight}" />

    <div class="text-info">
        <h4><s:message code="skills.progressbar.info.title" text="Experience Skill" /></h4>
        <p><s:message code="skills.progressbar.info.description" text="" /></p>
    </div>
	<div class="progress-content">
		<div class="rating-bar bar-left">
			<c:forEach var="i" begin="0" end="${colLeft - 1}">
				<c:set var="skill" value="${skills[i]}" />
				
				<my:bind var="skillTitle" type="Skill" beanId="${skill.id}" field="title" />
				<my:bind var="skillPercent" type="Skill" beanId="${skill.id}" field="percent" />
				
				<label>${skillTitle}</label>
				<span class="rating-count pull-right">${skillPercent}%</span>
	            <div class="skill-progress">
	                <div class="progress">
	                    <div style="background-color:${skill.color};" class="progress-bar" role="progressbar" aria-valuenow="${skillPercent}" aria-valuemin="0" aria-valuemax="100" >
	                    </div>
	                </div>
	            </div>
			</c:forEach>
		</div> 
	    <div class="skill rating-bar bar-right">
			<c:forEach var="i" begin="${colLeft}" end="${colLeft + colRight - 1}">
				<c:set var="skill" value="${skills[i]}" />
				
				<my:bind var="skillTitle" type="Skill" beanId="${skill.id}" field="title" />
				<my:bind var="skillPercent" type="Skill" beanId="${skill.id}" field="percent" />
				
				<label>${skillTitle}</label>
				<span class="rating-count pull-right">${skillPercent}%</span>
	            <div class="skill-progress">
	                <div class="progress">
	                    <div style="background-color:${skill.color};" class="progress-bar" role="progressbar" aria-valuenow="${skillPercent}" aria-valuemin="0" aria-valuemax="100" >
	                    </div>
	                </div>
	            </div>
			</c:forEach>
	    </div> 
	</div>
</c:if>


