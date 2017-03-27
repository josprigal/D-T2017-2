

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
<form action="actor/customer/request/search.do" method="post">
	Search: <input type="text" name="searchText" /> <br /> <input
		type="reset" /> <input type="submit" name="search" />
</form>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="requests" requestURI="${requestURI}" id="row">
    <spring:message code="title" var="titleHeader"/>
    <display:column property="title" title="${titleHeader}" sortable="true"/>

    <spring:message code="description" var="descriptionHeader"/>
    <display:column property="description" title="${descriptionHeader}" sortable="true"/>
       
       <spring:message code="origin.address" var="originHeader"/>
    <display:column property="origin.address" title="${originHeader}" sortable="true"/>
    
    <spring:message code="destination.address" var="destinationHeader"/>
    <display:column property="destination.address" title="${destinationHeader}" sortable="true"/>
    
    <spring:message code="moment" var="momentHeader"/>
    <display:column property="moment" title="${momentHeader}" sortable="true"/>
    
    <spring:message code="edit" var="editHeader"/>
    <display:column title="${editHeader }">
			<a href="actor/comment/${row.id}/list.do">
                <spring:message	code="comments" />
			</a>
	</display:column>

    <spring:message code="applicate" var="applicateHeader"/>
    <display:column title="${applicateHeader }">
        <a href="application/${row.id}/new.do">
            <spring:message	code="applicate" />
        </a>
    </display:column>
</display:table>

<a href="actor/customer/request/post.do"><spring:message code="post"/> </a>
