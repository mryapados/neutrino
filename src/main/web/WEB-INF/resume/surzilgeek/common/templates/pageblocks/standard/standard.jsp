<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<div class="section-padding">
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<div class="text-info">${activePage.description}</div>
				<my:block position="resume_standard" />
			</div>
		</div><!-- row -->
	</div><!-- container -->
</div><!-- exprience section -->