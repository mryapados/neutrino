<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<my:init test="${!initialized}"/>

<my:cache>
                <ul class="achievement">
                    <li class="achievement-info">
                        <span class="counter">35</span>
                        <h4>Projects completed</h4>
                    </li> 
                    <li class="achievement-info">
                        <span class="counter counter1">19</span>
                        <h4>Winning Awards</h4>
                    </li> 
                    <li class="achievement-info">
                        <span class="counter counter2">230</span>
                        <h4>Happy Clients</h4>
                    </li> 
                    <li class="achievement-info">
                        <span class="counter counter3">19</span>
                        <h4>Running Projects</h4>
                    </li>                            
                </ul>
</my:cache>
                                                            
                   