<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<section id="blog" class="blog-section section-padding">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="section-content">
                    <header class="section-title">
                        <h2><s:message code="blog.title" text="Blog" /></h2>
                    </header>
                    <article>
						<div class="text-info">${activePage.description}</div>
						<my:block position="resume_articles" />
					</article>
                </div>                                             
            </div>
        </div><!-- row -->
    </div><!-- container -->
</section><!-- portfolio section -->
