<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<%-- En HomePage, les liens du nav sont des ancres --%>
<c:forEach items="${categories}" var="category" varStatus="status">
	<my:bind var="categoryName" type="Category" beanId="${category.id}" field="name" />
	<my:bind var="categoryTitle" type="Category" beanId="${category.id}" field="title" />
	<c:set var="icon">
		<c:if test="${not empty category.icon}">
			<i class="${category.icon.fontAwesome}" aria-hidden="true"></i>
		</c:if>
	</c:set>
	<li class="scroll${status.first ? ' current dropdown' : ''}">
		<a href="#${categoryName}${categoryName eq 'home' ? '-banner' : ''}"><span>${icon}${categoryTitle}</span></a>
	</li>
</c:forEach>
         
<li>
    <ul class="social list-inline">
        <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a></li>
        <li><a href="#"><i class="fa fa-github-alt" aria-hidden="true"></i></a></li>
    </ul>
</li>      
                        
                        
			
					
                                                            
                   