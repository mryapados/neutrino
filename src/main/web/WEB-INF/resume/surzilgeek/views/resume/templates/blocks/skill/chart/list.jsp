<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:init test="${!initialized}"/>
            
<my:cache>
	<c:choose>
		<c:when test="${not empty skills}">
			<div class="language-proficiency">
			    <div class="title">
			        <div class="icons">
			            <i class="fa fa-language" aria-hidden="true"></i>
			        </div>                                  
			        <h3>Language Proficiency</h3>
			    </div>
			    <div class="sub-content">
			        <ul class="list-inline">
			        	<c:forEach items="${skills}" var="skill">
				            <li>
				            	<my:bind var="skillTitle" type="Skill" beanId="${skill.id}" field="title" />
				            	<fmt:formatNumber var="stars" value="${skill.percent / 20}" maxFractionDigits="0" />
				                <h5><c:out value="${skillTitle}" /></h5>
				                <ul>
				                	<c:forEach var = "i" begin = "1" end = "5">
				                		<i class="fa fa-star${stars >= i ? '' : '-o'}" aria-hidden="true"></i>
				                	</c:forEach>
				                </ul>
				            </li>
			        	</c:forEach> 
			        </ul>
			    </div>
			</div>
			<hr/> 
		</c:when>
		<c:when test="${blockPreview}">
			<%--- Orginal HTML template --%>
			<div class="language-proficiency">
			    <div class="title">
			        <div class="icons">
			            <i class="fa fa-language" aria-hidden="true"></i>
			        </div>                                  
			        <h3>Language Proficiency</h3>
			    </div>
			    <div class="sub-content">
			        <ul class="list-inline">
			            <li>
			                <h5>English</h5>
			                <ul>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			                </ul>
			            </li>
			            <li>
			                <h5>German</h5>
			                <ul>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			                </ul>
			            </li>
			            <li>
			                <h5>Sh5anish</h5>
			                <ul>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                </ul>
			            </li>
			            <li>
			                <h5>Latin</h5>
			                <ul>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			                    <li><i class="fa fa-star-o" aria-hidden="true"></i></li>
			                </ul>
			            </li>
			        </ul>
			    </div>
			</div>
			<hr/>
		</c:when>
	</c:choose>		
</my:cache>
