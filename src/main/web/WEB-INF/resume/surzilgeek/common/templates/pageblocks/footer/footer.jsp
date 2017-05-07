<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>


<div class="container">
	<div class="footer">
		<p>
			&copy; 2017 <a href="#"><c:out value="${resumeFirstName} ${resumeLastName}"/>.</a> All Rights Reserved
		</p>
	</div>
</div><!-- container -->