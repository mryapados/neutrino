<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<my:init test="${!initialized}"/>
            
                    <div class="language-skill">
                        <div class="text-info">
                            <h4>Language skill</h4>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.</p>
                        </div>
                        <ul>
                            <li class="chart" data-percent="100">
                                <span class="percent"></span>
                                <h5>German</h5>
                            </li>
                            <li class="chart" data-percent="90">
                                <span class="percent percent1"></span>
                                <h5>Spanish</h5>
                            </li>
                            <li class="chart" data-percent="100">
                                <span class="percent percent2"></span>
                                <h5>English</h5>
                            </li>
                            <li class="chart" data-percent="30">
                                <span class="percent percent3"></span>
                                <h5>Latin</h5>
                            </li>                                    
                        </ul>
                    </div><!-- more skill -->