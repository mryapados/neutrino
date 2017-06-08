<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
	Objets disponibles :  
	language : Scope Request, String , en / fr / es / ..., objet fourni par le controller, provient d'un cookie.

	TO DO...
--%>

<%-- init : Obligatoire dans le JSP contenant le Doctype --%>
<my:init test="${!initialized}"/>

<!DOCTYPE html>
<html lang="${language}">
<!--[if IE 9]>
<html class="ie9" lang="${language}">    <![endif]-->
<!--[if IE 8]>
<html class="ie8" lang="${language}">    <![endif]-->
<my:head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name=viewport content="width=device-width, initial-scale=1">
	
	<meta name="description" content="<c:out value='${activePage.metaDescription}'/>">
	<meta name="keywords" content="<c:out value='${activePage.metaKeyWords}'/>">
	<meta name="author" content="Cédric Sevestre">
	
	<title><c:out value="${activePage.metaTitle}"/></title>
</my:head>
<my:body id="samuel" cssClass="boxed">


<!--Pre-Loader-->
<div id="preloader"></div>

<header>
	<my:block position="resume_header" />
</header>

<!-- Container -->
<div class="content-wrap">

   <section class="container-fluid" data-section="home">

      <!-- Personal Flexy Section -->
      <div class="row flex-row">
         <!-- Personal Details 01 -->
         <div id="details" class="col-md-8 flex-column bg-color01 light nopadding"
              data-animation-origin="left"
              data-animation-duration="300"
              data-animation-delay="400"
              data-animation-distance="200px">
            <div class="padding-50 flex-panel">
               <div class="row row-no-padding">
                  <div class="col-md-12 nopadding">
                     <h3 class="font-accident-two-normal uppercase title">Gridus Resume HTML Template</h3>
                     <div class="quote">
                        <h4 class="font-accident-two-normal uppercase subtitle">Perfect for CV / Resume or Portfolio Website</h4>
                        <div class="dividewhite3"></div>
                     </div>
                  </div>
               </div>
               <div class="divider-dynamic"></div>
               <div class="row nopadding">
                  <div class="col-md-4 infoblock nopadding">
                     <div class="row">
                        <div class="col-xs-12 col-sm-1 col-md-3"><i class="flaticon-clocks18 color-blue"></i><div class="dividewhite1"></div></div>
                        <div class="col-xs-12 col-sm-11 col-md-9">
                           <h4 class="font-accident-two-medium uppercase">Modern</h4>
                           <p class="">
                              The Gridus Resume Template has an unique modern flat intuitive design. You can choose one of 8 Color schemes.
                           </p>
                        </div>
                     </div>
                     <div class="divider-dynamic"></div>
                  </div>
                  <div class="col-md-4 infoblock nopadding">
                     <div class="row">
                        <div class="col-xs-12 col-sm-1 col-md-3"><i class="flaticon-pie-graph color-blue"></i><div class="dividewhite1"></div></div>
                        <div class="col-xs-12 col-sm-11 col-md-9">
                           <h4 class="font-accident-two-medium uppercase">Simple CV</h4>
                           <p class="">
                              Gridus Personal Template is the simple, clean and stylish fully-responsive resume theme with modern creative design.
                           </p>
                        </div>
                     </div>
                     <div class="divider-dynamic"></div>
                  </div>
                  <div class="col-md-4 infoblock nopadding">
                     <div class="row">
                        <div class="col-xs-12 col-sm-1 col-md-3"><i class="flaticon-tool-1 color-blue"></i><div class="dividewhite1"></div></div>
                        <div class="col-xs-12 col-sm-11 col-md-9">
                           <h4 class="font-accident-two-medium uppercase">Responsive</h4>
                           <p class="">
                              Works fast and looks fine on any device with the wide range of the screen resolution. Supports all the Google SEO rules.
                           </p>
                        </div>
                     </div>
                     <div class="divider-dynamic"></div>
                  </div>

               </div>
            </div>
         </div>
         <!-- /Personal Details 01 -->

         <!-- Personal Details 02 -->
         <div id="personal" class="col-md-4 flex-column light ui-block-color01 personal"
              data-animation-origin="right"
              data-animation-duration="500"
              data-animation-delay="600"
              data-animation-distance="100px">
            <div class="padding-50 flex-panel">
               <h3 class="font-accident-two-normal uppercase title">Personal Info</h3>
               <div class="dividewhite4"></div>
               <div>
                  <div class="fullwidth box">
                     <div class="one"><p class="uppercase">Name:</p></div>
                     <div class="two"><p class="">Samuel F. Anderson</p></div>
                  </div>
                  <div class="fullwidth box">
                     <div class="one"><p class="uppercase text-nowrap">Birth Date:</p></div>
                     <div class="two"><p class="">03/12/1980</p></div>
                  </div>
                  <div class="fullwidth box">
                     <div class="one"><p class="uppercase">Address:</p></div>
                     <div class="two"><p class="">1234, Direct drive, Daytona Beach, Fl, USA</p></div>
                  </div>
                  <div class="fullwidth box">
                     <div class="one"><p class="uppercase">Phone:</p></div>
                     <div class="two"><p class="">1 234 567 89 10</p></div>
                  </div>
                  <div class="fullwidth box">
                     <div class="one"><p class="uppercase">Email:</p></div>
                     <div class="two"><p class="">james@anderson.com</p></div>
                  </div>
               </div>
               <div class="dividewhite1"></div>
            </div>
         </div>
         <!-- /Personal Details 02 -->
      </div>
      <!-- /Personal Flexy Section -->

   </section>

   <section id="professional" class="container-fluid" data-section="home">

      <div class="row flex-row">

         <div id="pro-experience" class="col-md-4 flex-column dark nopadding ui-block-color02 flex-col"
              data-animation-origin="bottom"
              data-animation-duration="300"
              data-animation-delay="800"
              data-animation-distance="200px">
            <div class="padding-50 flex-panel">
               <h3 class="font-accident-two-normal uppercase title">Employment</h3>
               <div class="dividewhite4"></div>
               <div class="experience">
                  <ul class="">
                     <li class="date">02/2008 - 04/2010</li>
                     <li class="company uppercase">
                        <a>
                           ABC Brodcast Inc. New York.
                        </a>
                     </li>
                     <li class="position">Junior Designer</li>
                  </ul>
                  <ul class="">
                     <li class="date">04/2010 - 06/2012</li>
                     <li class="company uppercase">
                        <a>
                           Leo Burnett London Office
                        </a>
                     </li>
                     <li class="position">Senior Designer</li>
                  </ul>
                  <ul class="">
                     <li class="date">06/2012 - present</li>
                     <li class="company uppercase">
                        <a>
                           Google Inc. Dublin.
                        </a>
                     </li>
                     <li class="position">UI/UX Designer</li>
                  </ul>
               </div>
               <a href="resume.html" class="btn btn-wh-trans btn-xs">Learn More</a>
               <div class="dividewhite1"></div>
            </div>
         </div>
         <div id="circle-skills" class="col-md-8 flex-column dark nopadding ui-block-color03 flex-col" data-section="progress"
              data-animation-origin="right"
              data-animation-duration="400"
              data-animation-delay="1100"
              data-animation-distance="200px">
            <div class="padding-50 flex-panel">
               <h3 class="font-accident-two-normal uppercase title">Professional skills</h3>
               <div class="dividewhite1"></div>
               <div class="row">

                  <div class="col-md-4 col-sm-4 col-xs-12 nopadding">
                     <div class="progressbar text-center">
                        <div id="circle1"
                             data-progressbar="circle"
                             data-progressbar-color="#fff"
                             data-progressbar-trailcolor="#fff"
                             data-progressbar-to='{"color": "#51f2ec", "width": 4}'
                             data-progressbar-from='{"color": "#3498db", "width": 4}'
                             data-progressbar-value="0.6">
                        </div>
                        <h4 class="font-accident-two-bold uppercase">Install</h4>
                        <p class="font-regular-normal">
                           A prototype is an early sample, model, or release of a product built to test ...
                        </p>
                        <a href="resume.html" class="btn btn-wh-trans btn-xs">Learn More</a>
                     </div>
                     <div class="dividewhite1"></div>
                  </div>

                  <div class="col-md-4 col-sm-4 col-xs-12 nopadding">
                     <div class="progressbar text-center">
                        <div id="circle2"
                             data-progressbar="circle"
                             data-progressbar-color="#fff"
                             data-progressbar-trailcolor="#fff"
                             data-progressbar-to='{"color": "#ffd200", "width": 4}'
                             data-progressbar-from='{"color": "#3498db", "width": 4}'
                             data-progressbar-value="0.85">
                        </div>
                        <h4 class="font-accident-two-bold uppercase">Customize</h4>
                        <p class="font-regular-normal">
                            Design is the creation of a plan or convention for the construction of an object ...
                        </p>
                        <a href="resume.html" class="btn btn-wh-trans btn-xs">Learn More</a>
                     </div>
                     <div class="dividewhite1"></div>
                  </div>

                  <div class="col-md-4 col-sm-4 col-xs-12 nopadding">
                     <div class="progressbar text-center">
                        <div id="circle3"
                             data-progressbar="circle"
                             data-progressbar-color="#fff"
                             data-progressbar-trailcolor="#fff"
                             data-progressbar-to='{"color": "#F09C88", "width": 4}'
                             data-progressbar-from='{"color": "#3498db", "width": 4}'
                             data-progressbar-value="0.93">
                        </div>
                        <h4 class="font-accident-two-bold uppercase">Support</h4>
                        <p class="font-regular-normal">
                            Software development is the process of computer programming ...
                        </p>
                        <a href="resume.html" class="btn btn-wh-trans btn-xs">Learn More</a>
                     </div>
                     <div class="dividewhite1"></div>
                  </div>

               </div>
            </div>
         </div>

      </div>

   </section>

</div>

<footer class="padding-50 dark ui-bg-color01"
        data-animation-origin="top"
        data-animation-duration="500"
        data-animation-delay="800"
        data-animation-distance="50px">
	<my:block position="resume_footer" />
</footer>

<!-- Back to Top -->
<div id="back-top"></div>
<!-- /Back to Top -->

</my:body>
</html>