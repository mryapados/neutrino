<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>


	<c:choose>
		<c:when test="${not empty skills}">
			<div class="expertise">
			    <div class="title">
			        <div class="icons">
			            <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
			        </div>                                  
			        <h3>Expertise</h3>
			    </div>                            
			    <div class="sub-content">
			    	<div class="row">
				    	<c:forEach var = "col" begin = "1" end = "3">
				            <div class="col-sm-4">
				                <div class="rating-bar">
				                    <label>Photoshop</label>
				                    <div class="skill-progress">
				                        <div class="progress">
				                            <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
				                            </div>
				                        </div>
				                    </div>
				                    <label>WordPress</label>
				                    <div class="skill-progress">
				                        <div class="progress">
				                            <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
				                            </div>
				                        </div>
				                    </div>
				                </div>                                
				            </div>
				    	</c:forEach>
				    </div><!-- row -->                                    
			    </div>
			</div>
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
			<div class="expertise">
			    <div class="title">
			        <div class="icons">
			            <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
			        </div>                                  
			        <h3>Expertise</h3>
			    </div>                            
			    <div class="sub-content">
			        <div class="row">
			            <div class="col-sm-4">
			                <div class="rating-bar">
			                    <label>Photoshop</label>
			                    <div class="skill-progress">
			                        <div class="progress">
			                            <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" style="width: 90%;">
			                            </div>
			                        </div>
			                    </div>
			                    <label>WordPress</label>
			                    <div class="skill-progress">
			                        <div class="progress">
			                            <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
			                            </div>
			                        </div>
			                    </div>
			                </div>                                
			            </div>
			            <div class="col-sm-4">
			                <div class="skill rating-bar">
			                    <label>Illustrator</label>
			                    <div class="skill-progress">
			                        <div class="progress">
			                            <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%;">
			                            </div>
			                        </div>
			                    </div>
			                    <label>Joomla</label>
			                    <div class="skill-progress">
			                        <div class="progress">
			                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
			                            </div>
			                        </div>
			                    </div>
			                </div>                                
			            </div>
			            <div class="col-sm-4">
			                <div class="skill rating-bar">
			                    <label>Html 5 &amp; CSS 3</label>
			                    <div class="skill-progress">
			                        <div class="progress">
			                            <div class="progress-bar" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100" style="width: 95%;">
			                            </div>
			                        </div>
			                    </div>
			                    <label>PHP</label>
			                    <div class="skill-progress">
			                        <div class="progress">
			                            <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: 70%;">
			                            </div>
			                        </div>
			                    </div>
			                </div>                                
			            </div>
			        </div><!-- row -->                                    
			    </div>
			</div>
		</c:when>
	</c:choose>

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