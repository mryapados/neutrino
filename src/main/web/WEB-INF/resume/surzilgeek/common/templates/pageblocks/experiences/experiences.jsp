<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<div id="exprience" class="exprience-section section-padding">
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<div class="section-title">
					<h1><s:message code="experience.title" text="My Exprience" /></h1>
				</div>
				<div class="text-info">${activePage.description}</div>
				<my:block position="resume_experiences" />
			</div>
		</div><!-- row -->
	</div><!-- container -->
</div><!-- exprience section -->