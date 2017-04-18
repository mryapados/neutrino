<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<c:set var="duration" value="1000" />
<c:set var="nextDelay" value="100" />
<ul id="nav" class="row nopadding cd-side-navigation">
	<c:forEach items="${categories}" var="category" varStatus="status">
		<my:bind var="categoryName" type="Category" beanId="${category.id}" field="name" />
		<my:bind var="categoryTitle" type="Category" beanId="${category.id}" field="title" />
		<c:set var="icon">
			<c:if test="${not empty category.icon}">
				<i class="${category.icon.flatIcon}" aria-hidden="true"></i>
			</c:if>
		</c:set>
		<li style="background-color:${category.menuColor};" class="col-xs-4 col-sm-2 nopadding menuitem" data-animation-duration="${duration}" data-animation-delay="${nextDelay}">
			<a href="${categoryName}.html" class="hvr-sweep-to-bottom">${icon}<span>${categoryTitle}</span></a>
		</li>
		<c:set var="nextDelay" value="${nextDelay + 200}" />
	</c:forEach>
	
	<%-- original template
	<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color01" data-animation-duration="1000" data-animation-delay="100"><a href="index.html" class="hvr-sweep-to-bottom"><i class="flaticon-insignia"></i><span>home</span></a></li>
	<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color02" data-animation-duration="1000" data-animation-delay="300"><a href="resume.html" class="hvr-sweep-to-bottom"><i class="flaticon-graduation61"></i><span>resume</span></a></li>
	<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color03" data-animation-duration="1000" data-animation-delay="500"><a href="portfolio-4-col.html" class="hvr-sweep-to-bottom"><i class="flaticon-book-bag2"></i><span>portfolio</span></a></li>
	<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color04" data-animation-duration="1000" data-animation-delay="700"><a href="contacts.html" class="hvr-sweep-to-bottom"><i class="flaticon-placeholders4"></i><span>contacts</span></a></li>
	<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color05" data-animation-duration="1000" data-animation-delay="900"><a href="feedback.html" class="hvr-sweep-to-bottom"><i class="flaticon-earphones18"></i><span>feedback</span></a></li>
	<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color06" data-animation-duration="1000" data-animation-delay="1100"><a href="blog-3-col.html" class="hvr-sweep-to-bottom"><i class="flaticon-pens15"></i><span>blog</span></a></li>
	--%>  
</ul>




			
			

			
