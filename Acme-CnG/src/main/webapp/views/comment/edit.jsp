<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


    <form:form action="actor/comment/new.do"  modelAttribute="form" method="POST">
	
	<jstl:if test="${comment.id != 0}">
	<form:hidden path="comment.id" />
	<form:hidden path="comment.version" />
	<form:hidden path="comment.offerOrRequest" />
        <form:hidden path="comment.actor" />
	</jstl:if>

        
	<acme:textbox path="comment.title" code="comment.title"/>
        <form:errors cssClass="error" path="comment.title" />
        
        <acme:textbox path="comment.text" code="comment.text"/>
        <form:errors cssClass="error" path="comment.text" />
        
        <acme:textbox path="comment.rate" code="comment.rate"/>
        <form:errors cssClass="error" path="comment.rate" />
        <form:hidden path="comment.banned" />
           	<br />     
        <acme:submit name="save" code="comment.post"/>
        

        
        

	
	
	
	<a href="javascript:history.back(1)"><spring:message
			code="comment.cancel" /></a>

	

</form:form>