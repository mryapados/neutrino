<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}" />

<section id="top-navigation" class="container-fluid nopadding">
	<div class="row nopadding ident ui-bg-color01">
		<!-- Photo -->
		<a href="index.html">
			<div class="col-md-4 vc-photo photo-01">&nbsp;</div>
		</a>
		<!-- /Photo -->
		<div class="col-md-8 vc-name nopadding">
			<!-- Name-Position -->
			<div class="row nopadding name">
				<div class="col-md-10 name-title">
					<h1 class="font-accident-two-extralight">Samuel Anderson</h1>
				</div>
				<div class="col-md-2 nopadding name-pdf">
					<a href="#" class="hvr-sweep-to-right"><i
						class="flaticon-download149" title="Download CV.pdf"></i></a>
				</div>
			</div>
			<div class="row nopadding position">
				<div class="col-md-10 position-title">

					<section class="cd-intro">
						<h2 class="cd-headline clip is-full-width font-accident-two-light">
							<span>The experienced </span> <span class="cd-words-wrapper">
								<b class="is-visible">UI/UX Designer</b> <b>Web Designer</b> <b>Photographer</b>
							</span>
						</h2>
					</section>

				</div>
				<div class="col-md-2 nopadding pdf">
					<a href="#" class="hvr-sweep-to-right"><i
						class="flaticon-behance7" title="My Behance Portfolio"></i></a>
				</div>
			</div>
			<!-- /Name-Position -->

			<!-- Main Navigation -->

			<ul id="nav" class="row nopadding cd-side-navigation">
				<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color01"
					data-animation-duration="1000" data-animation-delay="100"><a
					href="index.html" class="hvr-sweep-to-bottom"><i
						class="flaticon-insignia"></i><span>home</span></a></li>
				<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color02"
					data-animation-duration="1000" data-animation-delay="300"><a
					href="resume.html" class="hvr-sweep-to-bottom"><i
						class="flaticon-graduation61"></i><span>resume</span></a></li>
				<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color03"
					data-animation-duration="1000" data-animation-delay="500"><a
					href="portfolio-4-col.html" class="hvr-sweep-to-bottom"><i
						class="flaticon-book-bag2"></i><span>portfolio</span></a></li>
				<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color04"
					data-animation-duration="1000" data-animation-delay="700"><a
					href="contacts.html" class="hvr-sweep-to-bottom"><i
						class="flaticon-placeholders4"></i><span>contacts</span></a></li>
				<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color05"
					data-animation-duration="1000" data-animation-delay="900"><a
					href="feedback.html" class="hvr-sweep-to-bottom"><i
						class="flaticon-earphones18"></i><span>feedback</span></a></li>
				<li class="col-xs-4 col-sm-2 nopadding menuitem ui-menu-color06"
					data-animation-duration="1000" data-animation-delay="1100"><a
					href="blog-3-col.html" class="hvr-sweep-to-bottom"><i
						class="flaticon-pens15"></i><span>blog</span></a></li>
			</ul>

			<!-- /Main Navigation -->

		</div>
	</div>
</section>