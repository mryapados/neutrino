<%@page import="fr.cedricsevestre.entity.engine.translation.Translation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}" />

<div id="breadcrumb">
	<div class="container">
		<ol class="breadcrumb">
			<c:forEach items="${breadcrumbPages}" var="page">
				<c:choose>
					<c:when test="${activePage.id eq page.id}">
						<li class="active"><c:out value="${page.title}" /></li>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${page.objectType eq 'Category'}">
								<my:url var="url" value="/${page.name}.html" />
							</c:when>
							<c:when test="${page.objectType eq 'Article'}">
								<my:url var="url" value="/article/${page.name}.html" />
							</c:when>
							<c:when test="${page.objectType eq 'Portfolio'}">
								<my:url var="url" value="/portfolio/${page.name}.html" />
							</c:when>
							<c:otherwise>
								<my:url var="url" value="/${page.name}.html" />
							</c:otherwise>	
						</c:choose>
						<li><a href="${url}"><c:out value="${page.title}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ol>
	</div>
</div>
