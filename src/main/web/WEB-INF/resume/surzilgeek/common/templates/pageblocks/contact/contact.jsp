<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	                            <p><s:message code="contact.message.description" text="To get in touch, please fill this form." /></p>
	                        </div>
	                        
							<div class="alert alert-warning fade in alert-dismissable" role="alert">
							    <a class="close" aria-label="close" title="close" aria-hidden="true">×</a>
							    <p><i class="fa fa-exclamation-triangle" aria-hidden="true"></i> <s:message code="contact.mandatory" text="All fields are mandatory." /></p>
							</div>   
	                        
                      		<s:message var="messageError" code="contact.message.error" text="Error!" />
							<div id="form-errors" class="alert alert-danger fade in alert-dismissable hidden" role="alert" aria-hidden="true">
							    <a class="close" aria-label="close" title="close">×</a>
							    <p id="name-NotEmpty" class="hidden" aria-hidden="true"><strong>${messageError}</strong> <s:message code="contact.message.empty.name" text="Name must be fill !" /></p>
							    <p id="email-NotEmpty" class="hidden" aria-hidden="true"><strong>${messageError}</strong> <s:message code="contact.message.empty.email" text="Email must be fill !" /></p>
							    <p id="subject-NotEmpty" class="hidden" aria-hidden="true"><strong>${messageError}</strong> <s:message code="contact.message.empty.subject" text="Subject must be fill !" /></p>
								<p id="message-NotEmpty" class="hidden" aria-hidden="true"><strong>${messageError}</strong> <s:message code="contact.message.empty.message" text="Message must be fill !" /></p>
							</div>
							
							<div id="form-confirm" class="alert alert-success fade in alert-dismissable hidden" role="alert" aria-hidden="true">
							    <a class="close" aria-label="close" title="close">×</a>
							    <p><strong><s:message code="contact.message.success" text="Success!" /></strong> <s:message code="contact.message.success" text="Thanks you !" /></p>
							</div>        
	                        
	                        <form:form id="contact-form" class="contact-form" name="contact-form" method="post" modelAttribute="contact">
	                            <div class="row">
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                    	<s:message var="contactName" code="contact.name" text="Full name" />
	                                    	<label for="fullname" class="sr-only">${contactName}</label>
	                                        <form:input path="name" id="fullname" type="text" class="form-control" required="required" aria-invalid="false" placeholder="${contactName}"/>
	                                    </div>
	                                </div>
	                                <div class="col-sm-6">
	                                    <div class="form-group">
	                                    	<s:message var="contactEmail" code="contact.email" text="Your email id" />
	                                    	<label for="fullname" class="sr-only">${contactEmail}</label>
	                                        <form:input path="email" type="email" class="form-control" required="required" placeholder="${contactEmail}"/>
	                                    </div> 
	                                </div>
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                    	<s:message var="contactSubject" code="contact.subject" text="Subject" />
	                                    	<label for="fullname" class="sr-only">${contactSubject}</label>
	                                        <form:input path="subject" type="text" class="form-control" required="required" placeholder="${contactSubject}"/>
	                                    </div> 
	                                </div>
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                    	<s:message var="contactMessage" code="contact.message" text="Your message" />
	                                    	<label for="fullname" class="sr-only">${contactMessage}</label>
	                                        <form:textarea path="message" required="required" class="form-control" rows="7" placeholder="${contactMessage}"></form:textarea>
	                                    </div>             
	                                </div>     
	                            </div>
	                            <div class="form-group pull-right">
	                            	<s:message var="contactSubmit" code="contact.submit" text="Submit" />
	                            	<label for="submit" class="sr-only">${contactSubmit}</label>
	                                <button id="submit" type="button" class="btn btn-primary">${contactSubmit}</button>
	                            </div>
	                        </form:form>                            
	                    </div>
                    </article>
                </div>
            </div>
        </div><!-- row -->
    </div><!-- container -->
</section><!-- contact section -->

<c:url var="postUrl" value="/contact/save?servername=${folder.name}" />
<my:script>
    $(document).ready(function(){

		$(".alert a").on('click', function(){
			$(this).parent().addClass("hidden");
			$(this).parent().attr('aria-hidden', 'true').show();
		});
        $("#submit").on('click', function(){
        	$('#form-confirm').addClass("hidden");
        	$('#form-errors').addClass("hidden");
    		$('#name-NotEmpty').addClass("hidden");
			$('#email-NotEmpty').addClass("hidden");
			$('#subject-NotEmpty').addClass("hidden");
			$('#message-NotEmpty').addClass("hidden");

        	$('#form-confirm').attr('aria-hidden', 'true').show();
        	$('#form-errors').attr('aria-hidden', 'true').show();
    		$('#name-NotEmpty').attr('aria-hidden', 'true').show();
			$('#email-NotEmpty').attr('aria-hidden', 'true').show();
			$('#subject-NotEmpty').attr('aria-hidden', 'true').show();
			$('#message-NotEmpty').attr('aria-hidden', 'true').show();
    
            $.ajax({
                url: '${postUrl}',
                type : "POST",
                dataType : 'json',
                data : $("#contact-form").serialize(),
				beforeSend: function(xhr) {
				    xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
				},
                success : function(result) {
                	$('#form-confirm').removeClass("hidden");
                	$('#form-confirm').attr('aria-hidden', 'false').show();
                	
                	// Clear form
                	$('#contact-form').find("input[type=text], input[type=email], textarea").val("");
                },
                error: function(xhr, resp, text) {
                	// Display errors
                	$('#form-errors').removeClass("hidden");
                	$('#form-errors').attr('aria-hidden', 'false').show();
                	var errors = xhr.responseJSON;
					for (i = 0; i < errors.length; i++) { 
						var id = '#' + errors[i].field + '-' + errors[i].code;
						$(id).removeClass("hidden");
						$(id).attr('aria-hidden', 'false').show();
					}
					<!-- console.log(xhr, resp, text); -->
                }
            });
        });
    });
</my:script>





