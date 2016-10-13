<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<my:init test="${!initialized}"/>

<jsp:include page="detail/init.jsp" />
<div class="view-object">
	<h1>
		<s:message code="bo.list" text="${objectType}" />
		<s:message code="bo.${objectType}.entity.name" text="${objectType}" />
	</h1>
	
  <script type="text/ng-template" id="group-template.html">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title" style="color:#fa39c3">
          <a href tabindex="0" class="accordion-toggle" ng-click="toggleOpen()" uib-accordion-transclude="heading">
            <span uib-accordion-header ng-class="{'text-muted': isDisabled}">
              {{heading}}
            </span>
          </a>
        </h4>
      </div>
      <div class="panel-collapse collapse" uib-collapse="!isOpen">
        <div class="panel-body" style="text-align: right" ng-transclude></div>
      </div>
    </div>
  </script>
			
<uib-accordion close-others="oneAtATime">
    <div uib-accordion-group class="panel-default" heading="Static Header, initially expanded" is-open="status.isFirstOpen" is-disabled="status.isFirstDisabled">
      This content is straight in the template.
    </div>
    <div uib-accordion-group class="panel-default" heading="{{group.title}}" ng-repeat="group in groups">
      {{group.content}}
    </div>
    <div uib-accordion-group class="panel-default" heading="Dynamic Body Content">
      <p>The body of the uib-accordion group grows to fit the contents</p>
      <button type="button" class="btn btn-default btn-sm" ng-click="addItem()">Add Item</button>
      <div ng-repeat="item in items">{{item}}</div>
    </div>
    <div uib-accordion-group class="panel-default" heading="Custom template" template-url="group-template.html">
      Hello
    </div>
    <div uib-accordion-group class="panel-default" is-open="status.isCustomHeaderOpen" template-url="group-template.html">
      <uib-accordion-heading>
        Custom template with custom header template <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': status.isCustomHeaderOpen, 'glyphicon-chevron-right': !status.isCustomHeaderOpen}"></i>
      </uib-accordion-heading>
      World
    </div>
    <div uib-accordion-group class="panel-danger" heading="Delete account">
      <p>Please, to delete your account, click the button below</p>
      <button class="btn btn-danger">Delete</button>
    </div>
    <div uib-accordion-group class="panel-default" is-open="status.open">
      <uib-accordion-heading>
        I can have markup, too! <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
      </uib-accordion-heading>
      This is just some content to illustrate fancy headings.
    </div>
</uib-accordion>
			
			
			
			
			
			
			

	<data-uib-tabset active="active">
	
		<c:forEach var="tab" items="${data.fields}" varStatus="status">
			<data-uib-tab index="${status.index}" heading="${not empty tab.key ? tab.key : 'data'}">
			
			
			
	
				<c:forEach var="group" items="${tab.value}">
				
				
				
				
				
					<div class="panel-group">
						<div class="panel panel-primary">
							<div class="panel-heading">	
								${not empty group.key ? group.key : 'data'}
							</div>
							<div class="table-responsive">
								<table class="table table-striped table-bordered" role="grid">
									<tbody>
										<c:forEach var="field" items="${group.value}">
											<c:if test="${field.inView}">
												<tr>
													<td>
														${field.name} : ${field.type}
													</td>
													<td>
														<c:set var="finalObject" value="${data.objectData[field.name]}" scope="request" />
														<c:set var="finalField" value="${field}" scope="request" />
														<c:set var="finalFieldType" value="${finalField.type}" scope="request" />
														
														<jsp:include page="detail/field.jsp" />
					
														<c:remove var="finalObject"/>
														<c:remove var="finalField"/>
														<c:remove var="finalFieldType"/>
													</td>
												</tr>
											</c:if>
											
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						
						
						
					</div>
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
					
				</c:forEach>
			
			
			
				
			
			
			</data-uib-tab>
		</c:forEach>
	
	</data-uib-tabset>
			
	
			
			


<%-- 		<c:forEach var="tab" items="${data.fields}"> --%>
<%-- 		    >>> tab = ${tab.key}<br/> --%>
<!-- 			>>> ---------------------------<br/> -->
<%-- 			<c:forEach var="group" items="${tab.value}"> --%>
<%-- 			   >>> >>> group = ${group.key}<br/> --%>
<!-- 			   >>> >>> ---------------------------<br/> -->

<%-- 				<c:forEach var="field" items="${group.value}"> --%>
<%-- 				   >>> >>> >>> field = ${field.name} : ${field.type}<br/> --%>
<!-- 				   >>> >>> >>> ---------------------------<br/> -->

<%-- 				</c:forEach> --%>
<%-- 			</c:forEach> --%>
<%-- 		</c:forEach> --%>
	
	
	

			
			
			
			
			
			
   
			
			
			
			
			
			
			
			
			

</div>