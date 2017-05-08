<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<my:cache>
	<div class="language-skill">
		<div class="text-info">
			<h4><s:message code="skills.chart.info.title" text="Language Skill" /></h4>
		    <p><s:message code="skills.chart.info.description" text="" /></p>
		</div>
		<c:choose>
			<c:when test="${not empty activeResume}">
				<c:if test="${not empty skills}">
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
	    		</c:if>
			</c:when>
			<c:when test="${blockPreview}">
				<%--- Orginal HTML template --%>
		        <ul>
		            <li class="chart" data-percent="100">
		                <span class="percent"></span>
		                <h5>German</h5>
		            </li>
		            <li class="chart" data-percent="90">
		                <span class="percent percent1"></span>
		                <h5>Spanish</h5>
		            </li>
		            <li class="chart" data-percent="100">
		                <span class="percent percent2"></span>
		                <h5>English</h5>
		            </li>
		            <li class="chart" data-percent="30">
		                <span class="percent percent3"></span>
		                <h5>Latin</h5>
		            </li>                                    
		        </ul>
			</c:when>
		</c:choose>
	</div><!-- more skill -->
</my:cache>
<my:script>
// -------------------------------------------------------------
// EasyPieChart
// -------------------------------------------------------------

(function () {
    $('.language-skill').bind('inview', function(event, visible, visiblePartX, visiblePartY) {
        if (visible) {
            $('.chart').easyPieChart({
                //your configuration goes here
                easing: 'easeOut',
                delay: 3000,
                scaleColor: false,
                animate: 2000,
                onStep: function(from, to, percent) {
                    this.el.children[0].innerHTML = Math.round(percent);
                }

            });
        }
    }); 
}());
</my:script>

