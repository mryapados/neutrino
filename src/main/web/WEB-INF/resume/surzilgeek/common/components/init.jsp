<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>

<%-- Here set all necessary initialization for alone component like blocks --%>

<my:element template="resume_element_activeResume">
	<my:param name="test1" value="bonjour1"/>
	<my:param name="test2" value="bonjour2"/>
	<my:param name="test3" value="3"/>
</my:element>