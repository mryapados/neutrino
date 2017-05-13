<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>
<my:cache key="ViewNav${folder}${language}">
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
		<my:element template="resume_element_socialnetwork" />
	</li>
</my:cache>     
<my:script>
// -------------------------------------------------------------
//  Navigation Height 
// -------------------------------------------------------------

(function() {

    var height = $(window).height();
     $(".menu-one .navbar-nav").innerHeight(height);
    
}());

// -------------------------------------------------------------
//Mobile Toggle Control
// -------------------------------------------------------------

$(function(){ 
    var navMain = $(".collapse");
    navMain.on("click", "a", null, function () {
        navMain.collapse('hide');
    });
});

// -------------------------------------------------------------
// Navigation Scroll
// -------------------------------------------------------------

$(window).scroll(function(event) {
    Scroll();
}); 

$('#mainmenu li a').click(function() {  
    $('html, body').animate({scrollTop: $(this.hash).offset().top -1}, 1000);
    return false;
});

// User define function
function Scroll() {
    var contentTop      =   [];
    var contentBottom   =   [];
    var winTop      =   $(window).scrollTop();
    var rangeTop    =   200;
    var rangeBottom =   500;
    $('#mainmenu').find('.scroll a').each(function(){
        contentTop.push( $( $(this).attr('href') ).offset().top);
        contentBottom.push( $( $(this).attr('href') ).offset().top + $( $(this).attr('href') ).height() );
    })
    $.each( contentTop, function(i){
        if ( winTop > contentTop[i] - rangeTop ){
            $('#mainmenu li.scroll')
            .removeClass('current')
            .eq(i).addClass('current');          
        }
    })

};
</my:script>
               
					
                                                            
                   