

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="comments" requestURI="${requestURI}" id="row">
    <spring:message code="title" var="titleHeader"/>
    <display:column property="title" title="${titleHeader}" sortable="true"/>

    <spring:message code="text" var="textHeader"/>
    <display:column property="text" title="${textHeader}" sortable="true"/>

    <spring:message code="rate" var="rateHeader"/>
    <display:column property="rate" title="${rateHeader}" sortable="true"/>
    <jstl:if test="${row.banned==false }">
    <display:column title="Ban">
			<a href="administrator/comment/${row.id}/ban.do">
               Ban
			</a>
	</display:column>
	</jstl:if>
	    <jstl:if test="${row.banned==true }">
	    <display:column title="Ban">
			<a href="administrator/comment/${row.id}/unban.do">
               Unban
			</a>
	</display:column>
	    </jstl:if>
	
</display:table>


