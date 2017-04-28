<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
	<c:when test="${not empty activeResume}">
		<ul class="social list-inline">
			<c:forEach items="${socialnetworks}" var="socialNetwork" varStatus="status">
				<my:bind var="socialNetworkTitle" type="SocialNetwork" beanId="${socialNetwork.id}" field="title" />
				<c:set var="icon">
					<c:if test="${not empty socialNetwork.icon}">
						<i class="${socialNetwork.icon.fontAwesome}" aria-hidden="true"></i>
					</c:if>
				</c:set>
				<li><a href="${socialNetwork.url}">${icon}</a></li>
			</c:forEach>
		</ul>
	</c:when>
	<c:when test="${blockPreview}">
		<%--- Orginal HTML template --%>
		<ul class="social list-inline">
			<li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a></li>
			<li><a href="#"><i class="fa fa-github-alt" aria-hidden="true"></i></a></li>
		</ul>
	</c:when>
</c:choose>	