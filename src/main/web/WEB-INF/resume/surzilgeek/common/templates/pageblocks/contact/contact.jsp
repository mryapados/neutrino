<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>
            

<section id="contact" class="contact-section section-padding">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="section-content">
                    <header class="section-title">
                        <h2><s:message code="contact.title" text="Contact Me" /></h2>
                    </header>
                    <article>
						<div class="text-info">${activePage.description}</div>
						<my:block position="resume_contact" />
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
	                                    	<label for="fullname" class="sr-only">Full name</label>
	                                        <input id="fullname" type="text" class="form-control" required="required" aria-invalid="false" placeholder="Full Name">
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
                    </article>
                </div>
            </div>
        </div><!-- row -->
    </div><!-- container -->
</section><!-- contact section -->