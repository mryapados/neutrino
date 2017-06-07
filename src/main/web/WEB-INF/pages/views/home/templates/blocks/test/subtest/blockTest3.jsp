<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<my:init test="${!initialized}"/>




<h1>Test</h1>
<p>
<a class="btn btn-primary" href="login" role="button">Login</a>
<a class="btn btn-primary" href="primary/" role="button">Primary</a>
<a class="btn btn-default" href="default/" role="button">Default</a>
<a class="btn btn-success" href="success/" role="button">Success</a>
<a class="btn btn-info" href="info/" role="button">Info</a>
<a class="btn btn-warning" href="warning/" role="button">Warning</a>
<a class="btn btn-danger" href="danger/" role="button">Danger</a>
<a class="btn btn-link" href="link/" role="button">Link</a>
</p>


