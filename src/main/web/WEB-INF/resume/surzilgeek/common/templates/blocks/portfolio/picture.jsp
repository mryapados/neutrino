<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>

<c:url var="activePagePicture" value='${activePage.picture}'/>
<div class="row">
	<div class="col-md-12">
		<div class="thumbnail">
			<img src="${activePagePicture}" alt="" style="width: 100%">
		</div>
	</div>
</div>