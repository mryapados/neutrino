<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<c:if test="${not empty skills}">
	<div class="language-skill">
		<div class="text-info">
			<h4><s:message code="skills.chart.info.title" text="Language Skill" /></h4>
		    <p><s:message code="skills.chart.info.description" text="" /></p>
		</div>
	    <ul>
			<c:forEach items="${skills}" var="skill">
				<my:bind var="skillTitle" type="Skill" beanId="${skill.id}" field="title" />
				<my:bind var="skillPercent" type="Skill" beanId="${skill.id}" field="percent" />
				
		        <li class="chart" data-percent="${skillPercent}">
		            <span style="background-color:${skill.color};" class="percent"></span>
		            <h5>${skillTitle}</h5>
		        </li>
			</c:forEach>                         
	    </ul>
	</div><!-- more skill -->
</c:if>