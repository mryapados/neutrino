<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>

<my:init test="${!initialized}" />

<section id="top-navigation" class="container-fluid nopadding">
	<div class="row nopadding ident ui-bg-color01">
		<!-- Photo -->
		<jsp:include page="detail/photo.jsp"/>
		<!-- /Photo -->
		<div class="col-md-8 vc-name nopadding">
			<!-- Name-Position -->
			<jsp:include page="detail/position.jsp"/>
			<!-- /Name-Position -->

			<!-- Main Navigation -->
			<my:block position="resume_nav" />
			<!-- /Main Navigation -->
		</div>
	</div>
</section>