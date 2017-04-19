<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}" />


<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <div class="section-title">
                <h1>${activePage.title}</h1>
            </div>  
        </div>
    </div><!-- row -->
</div><!-- container -->
