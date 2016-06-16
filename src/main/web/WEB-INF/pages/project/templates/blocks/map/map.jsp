<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>

<my:init test="${!initialized}"/>

<jsp:include page="/WEB-INF/pages/common/templates/blocks/map/map.jsp" />