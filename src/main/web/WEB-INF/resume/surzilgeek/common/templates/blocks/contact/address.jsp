<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

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








                                                  
                   