<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<section class="section-padding">
	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<my:countBlock position="resume_standard_header" var="count">
					<header>
						<my:block position="resume_standard_header" />
					</header>
				</my:countBlock>
				<my:countBlock position="resume_standard_article" var="count">
					<article>
						<div class="text-info">${activePage.description}</div>
						<my:block position="resume_standard_article" />
					</article>
				</my:countBlock>
				<my:countBlock position="resume_standard_footer" var="count">
					<footer>
						<my:block position="resume_standard_footer" />
					</footer>
				</my:countBlock>
			</div>
		</div><!-- row -->
	</div><!-- container -->
</section><!-- exprience section -->