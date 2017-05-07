<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<my:cache>
	<div class="text-info">
	    <h4><s:message code="skills.progressbar.info.title" text="Experience Skill" /></h4>
	    <p><s:message code="skills.progressbar.info.description" text="" /></p>
	</div>
	<c:choose>
		<c:when test="${not empty activeResume}">
			<c:if test="${not empty skills}">
				<c:set var="skillsSize" value="${skills.size()}" />
				<fmt:formatNumber var="colRight" value="${skillsSize / 2}" maxFractionDigits="0" />
				<c:set var="colLeft" value="${skillsSize - colRight}" />
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
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
			<div class="progress-content">
	               <div class="rating-bar bar-left">
	                   <label>Photoshop</label>
	                   <span class="rating-count pull-right">90%</span>
	                   <div class="skill-progress">
	                       <div class="progress">
	                           <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" >
	                           </div>
	                       </div>
	                   </div>
	                   <label>PHP</label>
	                   <span class="rating-count pull-right">40%</span>
	                   <div class="skill-progress">
	                       <div class="progress">
	                           <div class="progress-bar bar1" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" >
	                           </div>
	                       </div>
	                   </div>
	                   <label>Html 5 & CSS 3</label>
	                   <span class="rating-count pull-right">70%</span>
	                   <div class="skill-progress">
	                       <div class="progress">
	                           <div class="progress-bar bar2" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" >
	                           </div>
	                       </div>
	                   </div>
	               </div>                                
	
	               <div class="skill rating-bar bar-right">
	                   <label>Illustrator</label>
	                   <span class="rating-count pull-right">80%</span>
	                   <div class="skill-progress">
	                       <div class="progress">
	                           <div class="progress-bar bar3" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" >
	                           </div>
	                       </div>
	                   </div>
	                   <label>Joomla</label>
	                   <span class="rating-count pull-right">50%</span>
	                   <div class="skill-progress">
	                       <div class="progress">
	                           <div class="progress-bar bar4" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" >
	                           </div>
	                       </div>
	                   </div>
	                   <label>Wordpress</label>
	                   <span class="rating-count pull-right">90%</span>
	                   <div class="skill-progress">
	                       <div class="progress">
	                           <div class="progress-bar bar5" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" >
	                           </div>
	                       </div>
	                   </div>
	               </div> 
	           </div>
		</c:when>
	</c:choose>
</my:cache>
<my:script>
// -------------------------------------------------------------
// Progress Bar
// -------------------------------------------------------------

(function () {

    $('.progress-content').bind('inview', function(event, visible, visiblePartX, visiblePartY) {
        if (visible) {
            $.each($('div.progress-bar'),function(){
                $(this).css('width', $(this).attr('aria-valuenow')+'%');
            });
            $(this).unbind('inview');
        }
    });
    $('.rating-bar').bind('inview', function(event, visible, visiblePartX, visiblePartY) {
        if (visible) {
            $.each($('div.progress-bar'),function(){
                $(this).css('width', $(this).attr('aria-valuenow')+'%');
            });
            $(this).unbind('inview');
        }
    });

}());
</my:script>