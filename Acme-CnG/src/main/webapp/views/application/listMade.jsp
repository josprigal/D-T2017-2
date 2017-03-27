

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="applications" requestURI="${requestURI}" id="row">

    <spring:message code="offerorequest" var="offerOrRequestHeader"/>
    <display:column property="offerOrRequest.id" title="${offerOrRequestHeader}" sortable="true"/>


    <spring:message code="status" var="statusHeader"/>
    <display:column property="type" title="${statusHeader}" sortable="true"/>


</display:table>