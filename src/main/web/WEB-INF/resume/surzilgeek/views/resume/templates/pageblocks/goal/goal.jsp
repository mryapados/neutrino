<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>

<div class="career-objective">
    <div class="title">
        <div class="icons">
            <i class="fa fa-black-tie" aria-hidden="true"></i>
        </div>                                    
        <h3>Career Objective</h3>
   </div> 
    <div class="sub-content">
    	<my:block position="resume_goal" />
    </div>
</div><!-- career objective -->
<hr>