<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}" />

<div id="home-banner" class="home-banner-one">
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<div class="banner-content" style="background-image:url(${resumeBannerUrl});">
					<div class="banner-info">
						<h1><s:message code="resume.hello" text="Nick Name:" /></h1>
						<h2>${resumeFirstName} ${resumeLastName}</h2>
						<h3>${resumeFunction}</h3>
					</div>
				</div>
			</div>
		</div><!-- row -->
	</div><!-- container -->
</div><!-- home-banner -->
