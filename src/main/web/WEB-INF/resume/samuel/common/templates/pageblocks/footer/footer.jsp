<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>


<div class="container-fluid nopadding">
	<div class="row">
		<div class="col-sm-3">
			<h5 class="font-accident-two-bold uppercase">Gridus HTML
				Template</h5>
			<p class="inline-block">The Gridus Resume Template has an unique
				modern flat intuitive design. You can choose one of 8 pre-defined
				Color schemes.</p>
			<div class="divider-dynamic"></div>
		</div>
		<div class="col-sm-3 cv-link">
			<h5 class="font-accident-two-bold uppercase">Download cv</h5>
			<div class="dividewhite1"></div>
			<a href="#!"><i class="fa fa-long-arrow-down" aria-hidden="true"></i>English</a>
			<a href="#!"><i class="fa fa-long-arrow-down" aria-hidden="true"></i>German</a>
			<a href="#!"><i class="fa fa-long-arrow-down" aria-hidden="true"></i>Spanish</a>
			<p class="inline-block">The CV is in .pdf format. Use the Adobe
				Reader to open it.</p>
			<div class="divider-dynamic"></div>
		</div>
		<div class="col-sm-3">
			<h5 class="font-accident-two-bold uppercase">Newsletter</h5>
			<div class="dividewhite1"></div>
			<input class="newsletter-email" type="email" required="" name="ne"
				placeholder="Your Email"> <a href="#!"
				class="btn btn-wh-trans btn-xs">OK</a>
			<div class="divider-dynamic"></div>
		</div>
		<div class="col-sm-3">
			<h5 class="font-accident-two-bold uppercase">Follow me</h5>
			<div class="follow">
				<ul class="list-inline social">
					<li><a target="_blank" href="#" class="rst-icon-facebook"><i
							class="fa fa-facebook"></i></a></li>
					<li><a target="_blank" href="#" class="rst-icon-twitter"><i
							class="fa fa-twitter"></i></a></li>
					<li><a target="_blank" href="#" class="rst-icon-pinterest"><i
							class="fa fa-pinterest"></i></a></li>
					<li><a target="_blank" href="#" class="rst-icon-instagram"><i
							class="fa fa-instagram"></i></a></li>
					<li><a target="_blank" href="#" class="rst-icon-youtube"><i
							class="fa fa-youtube"></i></a></li>
				</ul>
			</div>
			<div class="divider-dynamic"></div>
		</div>
	</div>
	<div class="dividewhite1"></div>
	<div class="row">
		<div class="col-md-12 copyrights">
			<p>© 2016 Samuel Anderson.</p>
		</div>
	</div>
</div>