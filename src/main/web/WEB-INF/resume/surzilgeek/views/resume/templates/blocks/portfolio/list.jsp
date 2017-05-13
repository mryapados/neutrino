<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>


	<div class="portfolio-content">
		<div class="row">
			<c:choose>
				<c:when test="${not empty activeResume}">
					<div class="portfolio">
					    <div class="title">
					        <div class="icons">
					            <i class="fa fa-picture-o" aria-hidden="true"></i>
					        </div>                                  
					        <h3><s:message code="portfolio.title" text="Portfolio"/></h3>
					    </div> 
					    <div class="sub-content">
					        <ul>
								<c:forEach items="${portfolios}" var="portfolio">
									<my:bind var="portfolioTitle" type="Portfolio" beanId="${portfolio.id}" field="title" />
									<my:url var="url" value="/portfolio/${portfolio.name}.html" />
						            <li><h5>${portfolioTitle} </h5> <span>:</span><a href="${url}"><my:stripTag value="${portfolio.chapo}"/></a></li>
					            </c:forEach>  
					        </ul>                                    
					    </div>                                
						</div>
				</c:when>
				<c:when test="${blockPreview}">
					<%--- Orginal HTML template --%>
					<div class="portfolio">
					    <div class="title">
					        <div class="icons">
					            <i class="fa fa-picture-o" aria-hidden="true"></i>
					        </div>                                  
					        <h3>Portfolio</h3>
					    </div> 
					    <div class="sub-content">
					        <ul>
					            <li><h5>Dribbble </h5> <span>:</span><a href="#">dribbble.com/geek</a></li>
					            <li><h5>Website </h5> <span>:</span><a href="#">www.surzilgeek.com/portfolio</a></li>
					            <li><h5>Flicker </h5> <span>:</span><a href="#">www.flicker.com/geek-photography</a></li>
					            <li><h5>Behance </h5> <span>:</span><a href="#">www.behance.com/geekin</a></li>
					        </ul>                                    
					    </div>                                
					</div>
				</c:when>
			</c:choose>	
	
	    </div>
	</div><!-- portfolio content -->

<my:script>
// -------------------------------------------------------------
// MagnificPopup
// -------------------------------------------------------------

(function () {
    $('.portfolio-info a').magnificPopup({
      type: 'image',
      gallery:{
        enabled:true
      }
    });
}());
</my:script>