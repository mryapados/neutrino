<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>



<s:message var="pattern" code="date.locale.portfolio.format" text="MMMM, yyyy"/>
<s:message var="present" code="portfolio.present " text="Present"/>

<my:cache>
	<div class="portfolio-content">
		<div class="row">
	
			<c:choose>
				<c:when test="${not empty activeResume}">
					<c:forEach items="${portfolios}" var="portfolio">
						<c:if test="${portfolio.id ne activePage.id}"><%-- Si on est dans une page portfolio, on ne l'affiche pas dans la liste --%>
							<my:bind var="portfolioTitle" type="Portfolio" beanId="${portfolio.id}" field="title" />
							<my:bind var="portfolioChapo" type="Portfolio" beanId="${portfolio.id}" field="chapo" />
		
							<c:url var="pictureUrl" value="${portfolio.picture}" scope="request"/>
		
							<div class="col-sm-6">
								<div class="portfolio-item">
									<img class="img-responsive" src="${pictureUrl}" alt="">
					                <div class="portfolio-overlay">
					                    <div class="portfolio-info">
					                        <a href="${pictureUrl}"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
					                        <h3><c:out value="${portfolioTitle}"/></h3>
										    <div>
										    	<c:out value="${portfolioChapo}" escapeXml="false" />
										    </div>
					                    </div>
					                </div>
					            </div>
					        </div>
						</c:if>
					</c:forEach>  
				</c:when>
				<c:when test="${blockPreview}">
					<%--- Orginal HTML template --%>
					<div class="col-sm-6">
						<div class="portfolio-item">
							<img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/1.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/1.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/2.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/2.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/3.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/3.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/4.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/4.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/5.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/5.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/6.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/6.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/7.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/7.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-sm-6">
			            <div class="portfolio-item">
			                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/8.jpg'/>" alt="Image">
			                <div class="portfolio-overlay">
			                    <div class="portfolio-info">
			                        <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/8.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
			                        <h3>Book Cover</h3>
			                        <p>Print Design</p>
			                    </div>
			                </div>
			            </div>
			        </div>
				</c:when>
			</c:choose>	
	
	    </div>
	</div><!-- portfolio content -->
</my:cache>
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