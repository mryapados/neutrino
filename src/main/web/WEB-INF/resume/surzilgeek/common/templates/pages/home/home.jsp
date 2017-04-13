<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
	Objets disponibles :  
	language : Scope Request, String , en / fr / es / ..., objet fourni par le controller, provient d'un cookie.

	TO DO...
--%>

<%-- init : Obligatoire dans le JSP contenant le Doctype --%>
<my:init test="${!initialized}"/>

<!DOCTYPE html>
<html lang="${language}">
<!--[if IE 9]>
<html class="ie9" lang="${language}">    <![endif]-->
<!--[if IE 8]>
<html class="ie8" lang="${language}">    <![endif]-->
<my:head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name=viewport content="width=device-width, initial-scale=1">
	
	<meta name="description" content="Gridus vCard HTML Template is the professional responsive personal website template, based on the latest Bootstrap 3">
	<meta name="keywords" content="to do">
	<meta name="author" content="Cédric Sevestre">
	
	<title><spring:message code="project.pages.home.title" arguments="${project}" /></title>
</my:head>
<my:body>

    <div class="main-wrapper home-one">
        <div id="navigation" class="menu-one">
            <div class="navbar navbar-fixed-top">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <div class="navbar-brand logo">
                        <a href="index.html"><img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/logo.png'/>" alt="Image" ></a> 
                    </div> 
                </div>
                               
                <nav id="mainmenu" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">          
                        <li  class="scroll current dropdown"><a href="#home-banner">
                            <span><i class="fa fa-hand-peace-o" aria-hidden="true"></i>Home</span></a>
                        </li>               
                        <li class="scroll"><a href="#about">
                            <span><i class="fa fa-user-secret" aria-hidden="true"></i>About me</span></a>
                        </li>               
                        <li class="scroll"><a href="#skill">
                            <span><i class="fa fa-sliders" aria-hidden="true"></i>Skills</span></a>
                         </li>     
                        <li class="scroll"><a href="#exprience">
                            <span><i class="fa fa-briefcase" aria-hidden="true"></i>Experience</span></a>
                        </li>               
                        <li class="scroll"><a href="#education">
                            <span><i class="fa fa-leanpub" aria-hidden="true"></i>Education</span></a>
                        </li>               
                        <li class="scroll"><a href="#portfolio">
                            <span><i class="fa fa-picture-o" aria-hidden="true"></i>Portfolio</span></a>
                        </li>                              
                        <li class="scroll"><a href="#contact">
                            <span><i class="fa fa-volume-control-phone" aria-hidden="true"></i>Contact</span></a>
                        </li>               
                        <li class="scroll"><a href="#blog">
                            <span><i class="fa fa-file-text-o" aria-hidden="true"></i>Blog</span></a>
                        </li>
                        <li class="scroll"><a href="#profile">
                            <span><i class="fa fa-floppy-o" aria-hidden="true"></i>My Resume.pdf</span></a>
                        </li>               
                        <li>
                            <ul class="social list-inline">
                                <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-github-alt" aria-hidden="true"></i></a></li>
                            </ul>
                        </li>                                          
                    </ul>
                </nav>
            </div><!-- navbar -->                                
        </div><!-- navigation -->

		<div id="header">
			<my:block position="resume_header" />
		</div>

        <div id="about" class="about-section section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-title">
                            <h1>Surzil Geek</h1>
                            <h4>Web Developer</h4>
                        </div>
                        <div class="about-info">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                            <div class="signature">
                                <h1>Surzil Geek</h1>
                            </div>
                        </div>
                        <address>
                            <p><span>Nick Name:</span> Endrue Surzil Geek</p>
                            <p><span>Email:</span> itsme@surzilgeek.com</p>
                            <p><span>Phone:</span> (123)-456-78910</p>
                            <p><span>Date of Birth:</span> Jan 13, 1982</p>
                            <p><span>Address:</span> 121 King Street, Melbourne Victoria, 1200 USA</p>
                        </address>
                        <ul class="achievement">
                            <li class="achievement-info">
                                <span class="counter">35</span>
                                <h4>Projects completed</h4>
                            </li> 
                            <li class="achievement-info">
                                <span class="counter counter1">19</span>
                                <h4>Winning Awards</h4>
                            </li> 
                            <li class="achievement-info">
                                <span class="counter counter2">230</span>
                                <h4>Happy Clients</h4>
                            </li> 
                            <li class="achievement-info">
                                <span class="counter counter3">19</span>
                                <h4>Running Projects</h4>
                            </li>                            
                        </ul>
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- about section -->

        <div id="skill" class="skill-section bg-color section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-content">
                            <div class="section-title">
                                <h1>My Skills</h1>
                            </div>
                            <div class="text-info">
                                <h4>Experience Skill</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>
                            <div class="progress-content">
                                <div class="rating-bar bar-left">
                                    <label>Photoshop</label>
                                    <span class="rating-count pull-right">90%</span>
                                    <div class="skill-progress">
                                        <div class="progress">
                                            <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" >
                                            </div>
                                        </div>
                                    </div>
                                    <label>PHP</label>
                                    <span class="rating-count pull-right">40%</span>
                                    <div class="skill-progress">
                                        <div class="progress">
                                            <div class="progress-bar bar1" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" >
                                            </div>
                                        </div>
                                    </div>
                                    <label>Html 5 & CSS 3</label>
                                    <span class="rating-count pull-right">70%</span>
                                    <div class="skill-progress">
                                        <div class="progress">
                                            <div class="progress-bar bar2" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" >
                                            </div>
                                        </div>
                                    </div>
                                </div>                                

                                <div class="skill rating-bar bar-right">
                                    <label>Illustrator</label>
                                    <span class="rating-count pull-right">80%</span>
                                    <div class="skill-progress">
                                        <div class="progress">
                                            <div class="progress-bar bar3" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" >
                                            </div>
                                        </div>
                                    </div>
                                    <label>Joomla</label>
                                    <span class="rating-count pull-right">50%</span>
                                    <div class="skill-progress">
                                        <div class="progress">
                                            <div class="progress-bar bar4" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" >
                                            </div>
                                        </div>
                                    </div>
                                    <label>Wordpress</label>
                                    <span class="rating-count pull-right">90%</span>
                                    <div class="skill-progress">
                                        <div class="progress">
                                            <div class="progress-bar bar5" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" >
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                            </div>                               

                            <div class="language-skill">
                                <div class="text-info">
                                    <h4>Language skill</h4>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                                </div>
                                <ul>
                                    <li class="chart" data-percent="100">
                                        <span class="percent"></span>
                                        <h5>German</h5>
                                    </li>
                                    <li class="chart" data-percent="90">
                                        <span class="percent percent1"></span>
                                        <h5>Spanish</h5>
                                    </li>
                                    <li class="chart" data-percent="100">
                                        <span class="percent percent2"></span>
                                        <h5>English</h5>
                                    </li>
                                    <li class="chart" data-percent="30">
                                        <span class="percent percent3"></span>
                                        <h5>Latin</h5>
                                    </li>                                    
                                </ul>
                            </div><!-- more skill -->                             
                        </div>                                             
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- skill section -->

        <div id="exprience" class="exprience-section section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-title">
                            <h1>My Exprience</h1>
                        </div>
                        <div class="text-info">
                            <h4>15 Years Exprience</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
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

        <div id="portfolio" class="portfolio-section section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-title">
                            <h1>Portfolio</h1>
                        </div>
                        <div class="text-info">
                            <h4>My Recent Work</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                        </div>
                        <div class="portfolio-content">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/1.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/1.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/2.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/2.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/3.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/3.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/4.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/4.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/5.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/5.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/6.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/6.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/7.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/7.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="portfolio-item">
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/8.jpg'/>" alt="Image">
                                        <div class="portfolio-overlay">
                                            <div class="portfolio-info">
                                                <a href="<c:url value='/resources/src/resume/surzilgeek/images/portfolio/8.jpg'/>"><i class="fa fa-camera-retro" aria-hidden="true"></i></a>
                                                <h3>Book Cover</h3>
                                                <p>Print Design</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- portfolio content -->
                   </div> 
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- portfolio section -->

        <div id="contact" class="contact-section bg-color section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-content">
                            <div class="section-title">
                                <h1>Contact Me</h1>
                            </div>
                            <div class="text-info">
                                <h4>Lets Keep In Touch</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                            </div>
                            <div class="address">
                                <ul>
                                    <li>
                                        <div class="icons">
                                            <i class="fa fa-map-signs" aria-hidden="true"></i>
                                        </div>
                                        <h3>Address</h3>
                                        <p>123 West 12th Street, Suite 456 New York, NY 123456</p>
                                    </li>
                                    <li>
                                        <div class="icons icons1">
                                            <i class="fa fa-phone" aria-hidden="true"></i>
                                        </div>
                                        <h3>Mobile number</h3>
                                        <p>+012 345 678 910</p>
                                    </li>
                                    <li>
                                        <div class="icons icons2">
                                            <i class="fa fa-envelope-o" aria-hidden="true"></i>
                                        </div>
                                        <h3>Email address</h3>
                                        <a href="#">itsme@surzilgeek.com</a>
                                    </li>
                                    <li>
                                        <div class="icons icons3">
                                            <i class="fa fa-external-link" aria-hidden="true"></i>
                                        </div>
                                        <h3>Social profiles</h3>
                                        <ul class="social list-inline">
                                            <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                            <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                            <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                                            <li><a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                                            <li><a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a></li>
                                            <li><a href="#"><i class="fa fa-github-alt" aria-hidden="true"></i></a></li>
                                        </ul>
                                    </li>
                                </ul>                            
                            </div>
                            <div class="contact">
                                <div class="contact-info">
                                    <div class="title">
                                        <div class="icons">
                                            <i class="fa fa-commenting-o" aria-hidden="true"></i> 
                                        </div>                                
                                        <h3>Leave me a message</h3>
                                    </div>                            
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                                </div>
                                <form id="contact-form" class="contact-form" name="contact-form" method="post" action="#">
                                    <div class="row">
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <input type="text" class="form-control" required="required" placeholder="Full Name">
                                            </div>
                                        </div>
                                        <div class="col-sm-6">
                                            <div class="form-group">
                                                <input type="email" class="form-control" required="required" placeholder="Your email id">
                                            </div> 
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <input type="text" class="form-control" required="required" placeholder="Subject">
                                            </div> 
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="form-group">
                                                <textarea name="message" required="required" class="form-control" rows="7" placeholder="Your message"></textarea>
                                            </div>             
                                        </div>     
                                    </div>
                                    <div class="form-group pull-right">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </form>                            
                            </div>
                        </div>
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- contact section -->

        <div id="blog" class="blog-section section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-title">
                            <h1>Blog</h1>
                        </div>
                        <div class="text-info">
                            <h4>News & Update</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="entry-post">
                                    <div class="entry-thumbnail">
                                        <div class="thumbnail-oberlay"></div>
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/1.jpg'/>" alt="Image">
                                    </div>
                                    <div class="post-content">
                                        <div class="time">
                                            <a href="#">09 <span>feb</span></a>
                                        </div>
                                        <div class="entry-title">
                                           <h2><a href="blog.html">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
                                       </div> 
                                    </div>
                                </div>                        
                            </div>
                            <div class="col-sm-6">
                                <div class="entry-post">
                                    <div class="entry-thumbnail">
                                        <div class="thumbnail-oberlay"></div>
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/2.jpg'/>" alt="Image">
                                    </div>
                                    <div class="post-content">
                                        <div class="time time1">
                                            <a href="#">19 <span>feb</span></a>
                                        </div>
                                        <div class="entry-title">
                                           <h2><a href="blog.html">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
                                       </div> 
                                    </div>
                                </div>                        
                            </div>
                            <div class="col-sm-6">
                                <div class="entry-post">
                                    <div class="entry-thumbnail">
                                        <div class="thumbnail-oberlay"></div>
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/3.jpg'/>" alt="Image">
                                    </div>
                                    <div class="post-content">
                                        <div class="time time2">
                                            <a href="#">11 <span>feb</span></a>
                                        </div>
                                        <div class="entry-title">
                                           <h2><a href="blog.html">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
                                       </div> 
                                    </div>
                                </div>                        
                            </div>
                            <div class="col-sm-6">
                                <div class="entry-post">
                                    <div class="entry-thumbnail">
                                        <div class="thumbnail-oberlay"></div>
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/4.jpg'/>" alt="Image">
                                    </div>
                                    <div class="post-content">
                                        <div class="time time3">
                                            <a href="#">09 <span>feb</span></a>
                                        </div>
                                        <div class="entry-title">
                                           <h2><a href="#">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
                                       </div> 
                                    </div>
                                </div>                        
                            </div>
                            <div class="col-sm-6">
                                <div class="entry-post">
                                    <div class="entry-thumbnail">
                                        <div class="thumbnail-oberlay"></div>
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/5.jpg'/>" alt="Image">
                                    </div>
                                    <div class="post-content">
                                        <div class="time time4">
                                            <a href="#">08 <span>feb</span></a>
                                        </div>
                                        <div class="entry-title">
                                           <h2><a href="#">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
                                       </div> 
                                    </div>
                                </div>                        
                            </div>
                            <div class="col-sm-6">
                                <div class="entry-post">
                                    <div class="entry-thumbnail">
                                        <div class="thumbnail-oberlay"></div>
                                        <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/blog/6.jpg'/>" alt="Image">
                                    </div>
                                    <div class="post-content">
                                        <div class="time time5">
                                            <a href="#">02 <span>feb</span></a>
                                        </div>
                                        <div class="entry-title">
                                           <h2><a href="#">Dashboard Design: 50+ Brilliant Examples and Resources</a></h2>
                                       </div> 
                                    </div>
                                </div>                        
                            </div>
                        </div><!-- row -->                        
                    </div>
                </div>
            </div><!-- container -->
        </div><!-- blog section -->

        <div id="profile" class="profile-section  bg-color section-padding">
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="section-content">
                            <div class="profile-logo">
                                <img class="img-responsive" src="<c:url value='/resources/src/resume/surzilgeek/images/profile.jpg'/>" alt="Image">
                            </div>
                            <div class="profile-info">
                                <h1>Surzil Geek</h1>
                                <h4>Web Developer</h4>
                                <address>
                                    <p>Address: 123 West 12th Street, Suite 456 New York, NY 123456 <br> Phone: +012 345 678 910 <br> <a href="#">Email: itsme@surzilegeek.com</a></p>
                                </address>
                            </div><!-- profile info -->
                            <div class="career-objective">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-black-tie" aria-hidden="true"></i>
                                    </div>                                    
                                    <h3>Career Objective</h3>
                               </div> 
                                <div class="sub-content">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                                </div>
                            </div><!-- career objective -->
                            <hr>
                            <div class="work-history">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-briefcase" aria-hidden="true"></i>
                                    </div>                                    
                                    <h3>Work History</h3>
                                </div>
                                <div class="sub-content">
                                    <div class="history">
                                        <h5>Senior Graphic Designer @ Buildomo</h5>
                                        <h6>2012 - Present</h6>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    </div>
                                    <hr>
                                    <div class="history">
                                        <h5>Former Graphic Designer @ Ideame</h5>
                                        <h6>2011 - 2012</h6>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    </div>
                                    <hr>
                                    <div class="history">
                                        <h5>Head of Design @ Titan Compnay</h5>
                                        <h6>2005 - 2011</h6>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    </div>
                                    <hr>
                                    <div class="history">
                                        <h5>Graphic Designer @ Precision</h5>
                                        <h6>2004 - 2005</h6>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    </div>
                                    <hr>
                                    <div class="history">
                                        <h5>Graphic Designer (Intern) @ Costa Rica Fruit Compnay</h5>
                                        <h6>2002 - 2004</h6>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                                    </div>
                                </div>
                            </div><!-- work history -->
                            <hr> 
                            <div class="educational-background">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-graduation-cap" aria-hidden="true"></i>
                                    </div>                                    
                                    <h3>Educational Background</h3>                                    
                                </div>
                                <div class="sub-content">
                                    <div class="education">
                                        <h5>Masters of Arts @ Montana Satet University</h5>
                                        <p>1999 - 2001</p>
                                    </div>
                                    <hr>
                                    <div class="education">
                                        <h5>Bachalor of Arts @ Universty of Bristol</h5>
                                        <p>1995 - 1999</p>
                                    </div>
                                    <hr>
                                    <div class="education">
                                        <h5>Diploma in Graphics Design @ Cincinnati Christian University</h5>
                                        <p>1993 - 1995</p>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="language-proficiency">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-language" aria-hidden="true"></i>
                                    </div>                                  
                                    <h3>Language Proficiency</h3>
                                </div>
                                <div class="sub-content">
                                    <ul class="list-inline">
                                        <li>
                                            <h5>English</h5>
                                            <ul>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <h5>German</h5>
                                            <ul>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <h5>Sh5anish</h5>
                                            <ul>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <h5>Latin</h5>
                                            <ul>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                                <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <hr>
                            <div class="expertise">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
                                    </div>                                  
                                    <h3>Expertise</h3>
                                </div>                            
                                <div class="sub-content">
                                    <div class="row">
                                        <div class="col-sm-4">
                                            <div class="rating-bar">
                                                <label>Photoshop</label>
                                                <div class="skill-progress">
                                                    <div class="progress">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="90" aria-valuemin="0" aria-valuemax="100" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <label>WordPress</label>
                                                <div class="skill-progress">
                                                    <div class="progress">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" >
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                                
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="skill rating-bar">
                                                <label>Illustrator</label>
                                                <div class="skill-progress">
                                                    <div class="progress">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <label>Joomla</label>
                                                <div class="skill-progress">
                                                    <div class="progress">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" >
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                                
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="skill rating-bar">
                                                <label>Html 5 & CSS 3</label>
                                                <div class="skill-progress">
                                                    <div class="progress">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="95" aria-valuemin="0" aria-valuemax="100" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <label>PHP</label>
                                                <div class="skill-progress">
                                                    <div class="progress">
                                                        <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" >
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>                                
                                        </div>
                                    </div><!-- row -->                                    
                                </div>
                            </div><!-- expertise -->
                            <hr>
                            <div class="personal-info">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-user-secret" aria-hidden="true"></i>
                                    </div>                                  
                                    <h3>Personal Info</h3>
                                </div>  
                                <div class="sub-content">
                                    <ul class="address">
                                        <li><h5>Full Name </h5> <span>:</span>Surzil Geek</li>
                                        <li><h5>Father's Name </h5> <span>:</span>Robert Geek</li>
                                        <li><h5>Mother's Name </h5> <span>:</span>Ismatic Roderos Geek</li>
                                        <li><h5>Date of Birth </h5> <span>:</span>26/01/1982</li>
                                        <li><h5>Birth Place </h5> <span>:</span>United State of America</li>
                                        <li><h5>Nationality </h5> <span>:</span>Canadian</li>
                                        <li><h5>Sex </h5> <span>:</span>Male</li>
                                        <li><h5>Address </h5> <span>:</span>121 King Street, Melbourne Victoria</li>
                                    </ul>
                                </div>                                
                            </div><!-- personal info -->
                            <hr>
                            <div class="portfolio">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-picture-o" aria-hidden="true"></i>
                                    </div>                                  
                                    <h3>Portfolio</h3>
                                </div> 
                                <div class="sub-content">
                                    <ul>
                                        <li><h5>Dribbble </h5> <span>:</span><a href="#">dribbble.com/geek</a></li>
                                        <li><h5>Website </h5> <span>:</span><a href="#">www.surzilgeek.com/portfolio</a></li>
                                        <li><h5>Flicker </h5> <span>:</span><a href="#">www.flicker.com/geek-photography</a></li>
                                        <li><h5>Behance </h5> <span>:</span><a href="#">www.behance.com/geekin</a></li>
                                    </ul>                                    
                                </div>                                
                            </div><!-- portfolio -->
                            <hr>
                            <div class="declaration">
                                <div class="title">
                                    <div class="icons">
                                        <i class="fa fa-hand-peace-o" aria-hidden="true"></i>
                                    </div>                                  
                                    <h3>Declaration</h3>
                                </div>
                                <div class="sub-content">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. </p>
                                    <div class="signature">
                                        <h1>Surzil Geek</h1>
                                    </div>                                    
                                </div>                                 
                            </div>
                        </div>
                        <div class="button">
                            <a href="cv.pdf" class="btn btn-primary">Download My Resume as a .pdf file</a>
                        </div>
                    </div>
                </div><!-- row -->
            </div><!-- container -->
        </div><!-- profile section -->

        <div id="footer">
        	<my:block position="resume_footer" />
        </div><!-- footer -->
    </div><!-- main wrapper -->


</my:body>
</html>