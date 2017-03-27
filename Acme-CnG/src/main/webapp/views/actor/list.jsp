

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="actors" requestURI="${requestURI}" id="row">
    <spring:message code="name" var="nameHeader"/>
    <display:column property="name" title="${nameHeader}" sortable="true"/>

    <spring:message code="email" var="emailHeader"/>
    <display:column property="email" title="${emailHeader}" sortable="true"/>

    <spring:message code="phone" var="phoneHeader"/>
    <display:column property="phone" title="${phoneHeader}" sortable="true"/>

    <spring:message code="edit" var="editHeader"/>
    <display:column title="${editHeader }">
        <a href="actor/comment/${row.id}/list.do">
            <spring:message	code="comments" />
        </a>
    </display:column>

</display:table>