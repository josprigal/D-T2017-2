

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="edit"/> <spring:message code="property" /> </h1>
    <form:form  modelAttribute="form" method="POST">
        <acme:textbox path="offer.title" code="name"/>
        <form:hidden path="offer.id"/>
        <acme:textbox path="offer.description" code="description"/>
        <acme:textbox path="offer.moment" code="address"/>
        <form:hidden path="offer.banned"/>
        <acme:textbox path="offer.place." code="rate"/>
        <acme:textbox path="origin.address" code="rate"/>
        <acme:textbox path="origin.gpsCoordinates" code="rate"/>
         <acme:textbox path="destination.address" code="rate"/>
        <acme:textbox path="destination.gpsCoordinates" code="rate"/>
        <acme:submit name="edit" code="edit"/>
    </form:form>
