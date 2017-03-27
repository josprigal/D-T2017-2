

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<h1><spring:message code="edit"/> <spring:message code="offer" /> </h1>
    <form:form  modelAttribute="form" method="POST">
        <acme:textbox path="offer.title" code="title"/>
        <form:errors cssClass="error" path="offer.title" />
        <form:hidden path="offer.id"/>
        <acme:textbox path="offer.description" code="description"/>
        <form:errors cssClass="error" path="offer.description" />
        <acme:textbox path="offer.moment" code="moment"/>
        <form:errors cssClass="error" path="offer.moment" />
        <form:hidden path="offer.banned"/>
        <acme:textbox path="origin.address" code="origin.address"/>
        <form:errors cssClass="error" path="origin.address" />
        <acme:textbox path="origin.gpsCoordinates" code="origin.gpsCoordinates"/>
        <form:errors cssClass="error" path="origin.gpsCoordinates" />
         <acme:textbox path="destination.address" code="destination.address"/>
         <form:errors cssClass="error" path="destination.address" />
        <acme:textbox path="destination.gpsCoordinates" code="destination.gpsCoordinates"/>
        <form:errors cssClass="error" path="destination.gpsCoordinates" />
      
        <acme:submit name="edit" code="edit"/>
    </form:form>
