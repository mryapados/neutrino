<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>
<my:cache>
	<div class="article-content">
		<div class="row">
	
			<c:choose>
				<c:when test="${not empty activeResume}">
					<c:forEach items="${articles}" var="article" varStatus="status">
						<c:if test="${blog.id ne activePage.id}"><%-- Si on est dans une page article, on ne l'affiche pas dans la liste --%>
							<c:set var="i" value="${empty i || i eq 5 ? 0 : i + 1}"/>
							
							<fmt:formatDate var="month" pattern="MMM" value="${article.publishDate}" />
							<fmt:formatDate var="day" pattern="dd" value="${article.publishDate}" />
							
							<my:bind var="articleTitle" type="Article" beanId="${article.id}" field="title" />
							<c:url var="pictureUrl" value="${article.picture}" scope="request"/>
		
							<div class="col-sm-6">
							    <div class="entry-post">
							        <div class="entry-thumbnail">
							            <div class="thumbnail-oberlay"></div>
							            <img class="img-responsive" src="${pictureUrl}" alt="">
							        </div>
							        <div class="post-content">
							        	<my:url var="url" value="/article/${article.name}.html" />
							            <div class="time time${i}">
							                <a href="${url}">${day} <span>${month}</span></a>
							            </div>
							            <div class="entry-title">
							               <h2><a href="${url}"><c:out value="${articleTitle}"/></a></h2>
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
					    <div class="entry-post">
					        <div class="entry-thumbnail">
					            <div class="thumbnail-oberlay"></div>
					            <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/1.jpg'/>" alt="Image">
					        </div>
					        <div class="post-content">
					            <div class="time">
					                <a href="#">09 <span>feb</span></a>
					            </div>
					            <div class="entry-title">
					               <h2><a href="blog.html">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
					           </div> 
					        </div>
					    </div>                        
					</div>
					<div class="col-sm-6">
					    <div class="entry-post">
					        <div class="entry-thumbnail">
					            <div class="thumbnail-oberlay"></div>
					            <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/2.jpg'/>" alt="Image">
					        </div>
					        <div class="post-content">
					            <div class="time time1">
					                <a href="#">19 <span>feb</span></a>
					            </div>
					            <div class="entry-title">
					               <h2><a href="blog.html">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
					           </div> 
					        </div>
					    </div>                        
					</div>
					<div class="col-sm-6">
					    <div class="entry-post">
					        <div class="entry-thumbnail">
					            <div class="thumbnail-oberlay"></div>
					            <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/3.jpg'/>" alt="Image">
					        </div>
					        <div class="post-content">
					            <div class="time time2">
					                <a href="#">11 <span>feb</span></a>
					            </div>
					            <div class="entry-title">
					               <h2><a href="blog.html">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
					           </div> 
					        </div>
					    </div>                        
					</div>
					<div class="col-sm-6">
					    <div class="entry-post">
					        <div class="entry-thumbnail">
					            <div class="thumbnail-oberlay"></div>
					            <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/4.jpg'/>" alt="Image">
					        </div>
					        <div class="post-content">
					            <div class="time time3">
					                <a href="#">09 <span>feb</span></a>
					            </div>
					            <div class="entry-title">
					               <h2><a href="#">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
					           </div> 
					        </div>
					    </div>                        
					</div>
					<div class="col-sm-6">
					    <div class="entry-post">
					        <div class="entry-thumbnail">
					            <div class="thumbnail-oberlay"></div>
					            <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/5.jpg'/>" alt="Image">
					        </div>
					        <div class="post-content">
					            <div class="time time4">
					                <a href="#">08 <span>feb</span></a>
					            </div>
					            <div class="entry-title">
					               <h2><a href="#">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
					           </div> 
					        </div>
					    </div>                        
					</div>
					<div class="col-sm-6">
					    <div class="entry-post">
					        <div class="entry-thumbnail">
					            <div class="thumbnail-oberlay"></div>
					            <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/6.jpg'/>" alt="Image">
					        </div>
					        <div class="post-content">
					            <div class="time time5">
					                <a href="#">02 <span>feb</span></a>
					            </div>
					            <div class="entry-title">
					               <h2><a href="#">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
					           </div> 
					        </div>
					    </div>                        
					</div>
				</c:when>
			</c:choose>	
	
	    </div>
	</div><!-- article content -->
</my:cache>




























                                                  
                   