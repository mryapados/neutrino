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

				<h1>${activePage.title}</h1>
			
				<div class="chapo">${activePage.chapo}</div>
			
				<my:countBlock position="resume_standard_header" var="count">
					<header>
						<my:block position="resume_standard_header" />
					</header>
				</my:countBlock>
				
				<article>
					<div class="text-info">${activePage.description}</div>
					<my:countBlock position="resume_standard_article" var="count">
						<my:block position="resume_standard_article" />
					</my:countBlock>
				</article>
				
				<my:countBlock position="resume_standard_footer" var="count">
					<footer>
						<my:block position="resume_standard_footer" />
					</footer>
				</my:countBlock>
			</div>
		</div><!-- row -->
	</div><!-- container -->
</section><!-- exprience section -->