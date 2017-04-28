<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>
            
<c:url var="postUrl" value="/contact/save" />
<section id="contact" class="contact-section section-padding">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="section-content">
                    <header class="section-title">
                        <h2><s:message code="contact.title" text="Contact Me" /></h2>
                    </header>
                    <article>
						<div class="text-info"><c:out value="${activePage.description}" escapeXml="false" /></div>
						<my:block position="resume_contact" />
	                    <div class="address">
	                        <ul>
	                            <li>
	                                <div class="icons">
	                                    <i class="fa fa-map-signs" aria-hidden="true"></i>
	                                </div>
	                                <h3><s:message code="contact.address" text="Address" /></h3>
	                                <p><c:out value="${resumeAddress}"/></p>
	                            </li>
	                            <li>
	                                <div class="icons icons1">
	                                    <i class="fa fa-phone" aria-hidden="true"></i>
	                                </div>
	                                <h3><s:message code="contact.phone" text="Mobile number" /></h3>
	                                <p><c:out value="${resumePhone}"/></p>
	                            </li>
	                            <li>
	                                <div class="icons icons2">
	                                    <i class="fa fa-envelope-o" aria-hidden="true"></i>
	                                </div>
	                                <h3><s:message code="contact.email" text="Email address" /></h3>
	                                <a href="mailto:<c:out value="${resumeEmail}"/>"><c:out value="${resumeEmail}"/></a>
	                            </li>
	                            <li>
	                                <div class="icons icons3">
	                                    <i class="fa fa-external-link" aria-hidden="true"></i>
	                                </div>
	                                <h3><s:message code="contact.socialnetwork.title" text="Social profiles" /></h3>
	                                <my:element template="resume_element_socialnetwork" />
	                            </li>
	                        </ul>                            
	                    </div>
	                    <div class="contact">
	                        <div class="contact-info">
	                            <div class="title">
	                                <div class="icons">
	                                    <i class="fa fa-commenting-o" aria-hidden="true"></i>
	                                </div>                                
	                                <h3><s:message code="contact.message.title" text="Leave me a message" /></h3>
	                            </div>                            
	                            <p><s:message code=">contact.message.description" text="To get in touch, please fill this form." /></p>
	                        </div>
	                        <form:form id="contact-form" class="contact-form" name="contact-form" method="post" action="">
	                            <div class="row">
	                                <div class="col-sm-6">
	                                	
	                                    <div class="form-group">
	                                    	<label for="fullname" class="sr-only">Full name</label>
	                                        <input name="name" id="fullname" type="text" class="form-control" required="required" aria-invalid="false" placeholder="Full Name">
	                                    </div>
	                                </div>
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                        <input name="email" type="email" class="form-control" required="required" placeholder="Your email id">
	                                    </div> 
	                                </div>
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <input name="subject" type="text" class="form-control" required="required" placeholder="Subject">
	                                    </div> 
	                                </div>
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <textarea name="message" required="required" class="form-control" rows="7" placeholder="Your message"></textarea>
	                                    </div>             
	                                </div>     
	                            </div>
	                            <div class="form-group pull-right">
	                                <button id="submit" type="button" class="btn btn-primary">Submit</button>
	                            </div>
	                        </form:form>                            
	                    </div>
                    </article>
                </div>
            </div>
        </div><!-- row -->
    </div><!-- container -->
</section><!-- contact section -->








<my:script>
    $(document).ready(function(){
        // click on button submit
        $("#submit").on('click', function(){
        
        
        	var data = $("#form").serialize();
     	    console.log(data);
        
        
            // send ajax
            $.ajax({
                url: '${postUrl}', // url where to submit the request
                type : "POST", // type of action POST || GET
                dataType : 'json', // data type
                data : $("#form").serialize(), // post data || get data
                success : function(result) {
                    // you can see the result from the console
                    // tab of the developer tools
                    console.log(result);
                },
                error: function(xhr, resp, text) {
                    console.log(xhr, resp, text);
                }
            })
        });
    });

</my:script>





