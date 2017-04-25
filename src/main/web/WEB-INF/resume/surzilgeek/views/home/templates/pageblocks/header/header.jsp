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
						<h1>
							<span class="title"><s:message code="resume.hello" text="Hello, I am " /></span>
							<span class="name">${resumeFirstName} ${resumeLastName}</span>
							<span class="function">${resumeFunction}</span>
						</h1>
					</div>
				</div>
			</div>
		</div><!-- row -->
	</div><!-- container -->
</div><!-- home-banner -->
