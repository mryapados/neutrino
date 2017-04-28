<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<c:forEach items="${categories}" var="category" varStatus="status">
	<my:bind var="categoryName" type="Category" beanId="${category.id}" field="name" />
	<my:bind var="categoryTitle" type="Category" beanId="${category.id}" field="title" />
	<c:set var="icon">
		<c:if test="${not empty category.icon}">
			<i class="${category.icon.fontAwesome}" aria-hidden="true"></i>
		</c:if>
	</c:set>
	<li class="scroll${status.first ? ' current dropdown' : ''}">
		<c:url var="url" value='/${categoryName}.html' />
		<a href="${url}"><span>${icon}${categoryTitle}</span></a>
	</li>
</c:forEach>
<li>
	<my:element template="resume_element_socialnetwork" />
</li>      
                        
                        
			
					
                                                            
                   