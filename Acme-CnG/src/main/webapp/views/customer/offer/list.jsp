

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1>
	<spring:message code="search" />
</h1>
<form action="actor/customer/offer/search.do" method="post">
	Search: <input type="text" name="searchText" /> <br /> <input
		type="reset" /> <input type="submit" name="search" />
</form>
<display:table pagesize="5" class="displaytag" keepStatus="true" name="offers" requestURI="${requestURI}" id="row">
    <spring:message code="title" var="titleHeader"/>
    <display:column property="title" title="${titleHeader}" sortable="true"/>

    <spring:message code="description" var="descriptionHeader"/>
    <display:column property="description" title="${descriptionHeader}" sortable="true"/>
    
    <spring:message code="moment" var="momentHeader"/>
    <display:column property="moment" title="${momentHeader}" sortable="true"/>
    
</display:table>

<a href="actor/customer/offer/post.do"><spring:message code="post"/> </a>
