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
                        <div class="text-info">
							${activePage.description}
                        </div>
                        <div class="exprience">
                            <div class="exprience-image">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/1.png'/>" alt="Image">
                            </div>
                            <div class="exprience-info">
                                <h3>Senior Graphic Designer</h3>
                                <h5>July, 2012 - Present (4 years)</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>                            
                        </div>
                        <hr>
                        <div class="exprience">
                            <div class="exprience-image">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/2.png'/>" alt="Image">
                            </div>
                            <div class="exprience-info">
                                <h3>Former Graphic Designer</h3>
                                <h5>March, 2011 - June, 2012 (1 year, 3 Month)</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>                            
                        </div>
                        <hr>
                        <div class="exprience">
                            <div class="exprience-image">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/3.png'/>" alt="Image">
                            </div>
                            <div class="exprience-info">
                                <h3>Head of Design</h3>
                                <h5>March, 2005 - March, 2011 (6 years)</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>                            
                        </div>
                        <hr>
                        <div class="exprience">
                            <div class="exprience-image">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/4.png'/>" alt="Image">
                            </div>
                            <div class="exprience-info">
                                <h3>Graphic Designer</h3>
                                <h5>January, 2004 - March, 2005 (1 Year, 2 Months)</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>                            
                        </div>
                        <hr>
                        <div class="exprience">
                            <div class="exprience-image">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/exprience/5.png'/>" alt="Image">
                            </div>
                            <div class="exprience-info">
                                <h3>Graphic Designer (intern)</h3>
                                <h5>January, 2002 - August, 2004 (2 years, 7 months)</h5>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>                            
                        </div>
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- exprience section -->