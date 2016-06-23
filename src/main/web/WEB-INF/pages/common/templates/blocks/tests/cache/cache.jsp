<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="my" uri="/WEB-INF/taglibs/neutrino.tld" %>


<my:init test="${!initialized}"/>


<my:cache>
	<h1>Je suis issus du cache sans attribut</h1>	
</my:cache>
<my:cache>
	<h1>Je suis issus du cache sans attribut dans la même page</h1>	
</my:cache>
<my:cache key="hello">
	<h1>Je suis issus du cache attribut attribut key = "hello"</h1>	
</my:cache>
<my:cache lang="fr">
	<h1>Je suis issus du cache attribut attribut lang = "fr"</h1>	
</my:cache>