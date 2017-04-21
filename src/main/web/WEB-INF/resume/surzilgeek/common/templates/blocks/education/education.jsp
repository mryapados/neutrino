<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>
            
			
        <div id="education" class="education-section bg-color section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-content">
                            <div class="section-title">
                                <h1>Education</h1>
                            </div>
                            <div class="text-info">
                                <h4>Just My Educational Background</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>
                            <div class="education-info">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/education/1.png'/>" alt="Image">
                                <h3>Montana State University</h3>
                                <h5>Bachalor of Arts</h5>
                                <h6>1999 - 2003</h6>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>
                            <hr>
                            <div class="education-info">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/education/2.png'/>" alt="Image">
                                <h3>Montana State University</h3>
                                <h5>University of Bristol</h5>
                                <h6>1999 - 2003</h6>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>
                            <hr>
                            <div class="education-info">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/education/3.png'/>" alt="Image">
                                <h3>Cincinnati Christian University</h3>
                                <h5>Bachalor of Arts</h5>
                                <h6>1999 - 2003</h6>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>
                        </div>
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- education section -->