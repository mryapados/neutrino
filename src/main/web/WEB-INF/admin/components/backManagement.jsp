<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- Back office ROLE_ADMIN	-->
<div id="back-management">
    <div class="block-selection" data-ng-controller="BlockSelectionCtrl">
        <div class="outside">
            <div class="edit" data-ng-click="switchDisplay()">
                    
            </div>
        </div>
       <div class="inside closed" data-ng-class="{opened : openValue, closed : !openValue}">
            <div class="contain">
			    <h3><spring:message code="block-selection.inside.title" /></h3>
			    <h4><spring:message code="block-selection.inside.subtitle" /></h4>

	            <div class="col-xs-12">
					  <input type="text" class="form-control" placeholder="Recherche" data-ng-model="search">
	            </div>
				<div class="col-xs-12">
					<ul class="list-block">
						<li data-ng-repeat="block in BlockManagementFacade.getTemplates() | orderBy: 'name' | filter : search">
							<div drag="block" dragStyle="columnDrag">
								<strong>{{block.name}}</strong><br />
								<span>adresse : {{block.path}}</span><br />
								<span>crée le : {{block.dateAdd | date}}</span><br />
								<span>description : {{block.description}}</span>
							</div>
						</li>
					</ul>
				</div>

				
            </div>
        </div>
    </div>
    <div class="debug-info">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<h3>Debug info</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
					<p>User : <em><c:out value="${surfer.name}"/></em></p>
					<p>Role : <em><c:out value="${surfer.role}"/></em></p>
					<p>Preview : <em><c:out value="${blockPreview}"/></em></p>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
					<p>Langue : <em><spring:message code="language" /></em></p>
					<sec:authorize access="isRememberMe()">
						<p><spring:message code="debug-info.RememberMe" /></p>
					</sec:authorize>
					<sec:authorize access="isFullyAuthenticated()">
						<p><spring:message code="debug-info.isFullyAuthenticated" /></p>
					</sec:authorize>
				</div>
			</div>
		</div>
	</div>
</div>






<script src="<c:url value='/js/lib/ui-bootstrap-tpls.js'/>"></script>
<script src="<c:url value='/js/back/app.js'/>"></script>	
<script src="<c:url value='/js/back/controller/blockManagementCtrl.js'/>"></script>	
<script src="<c:url value='/js/back/controller/blockSelectionCtrl.js'/>"></script>
<script src="<c:url value='/js/back/controller/templateModalCtrl.js'/>"></script>
<script src="<c:url value='/js/back/controller/uiPositionCtrl.js'/>"></script>
<script src="<c:url value='/js/back/filters/backFilters.js'/>"></script>
<script src="<c:url value='/js/back/service.js'/>"></script>
<script src="<c:url value='/js/back/services/repositories.js'/>"></script>
<script src="<c:url value='/js/back/services/business.js'/>"></script>
<script src="<c:url value='/js/back/services/constants.js'/>"></script>
<script src="<c:url value='/js/back/directives/backDirectives.js'/>"></script>		
		
		
		
		
		
		
		
			