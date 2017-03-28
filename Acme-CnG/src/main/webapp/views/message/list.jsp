

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<a href="message/new.do">
    <spring:message	code="new" /> <spring:message	code="message" />
</a>

<display:table pagesize="5" class="displaytag" keepStatus="true" name="messages" requestURI="${requestURI}" id="row">

    <spring:message code="title" var="titleHeader"/>
    <display:column property="title" title="${titleHeader}" sortable="true"/>


    <spring:message code="text" var="textHeader"/>
    <display:column property="text" title="${textHeader}" sortable="true"/>

    <spring:message code="attachments" var="attachmentsHeader"/>
    <display:column property="attachments" title="${attachmentsHeader}" sortable="true"/>

    <spring:message code="sent" var="sentHeader"/>
    <display:column property="sent" title="${sentHeader}" sortable="true"/>

    <spring:message code="sender" var="senderHeader"/>
    <display:column property="receiver.id" title="${senderHeader}" sortable="true"/>

    <spring:message code="reply" var="replyHeader"/>
    <display:column title="${replyHeader}">
        <a href="message/new.do?recipient=${row.sender.id}">
            <spring:message	code="reply" />
        </a>
    </display:column>

    <spring:message code="forward" var="forwardHeader"/>
    <display:column title="${forwardHeader}">
        <a href="message/new.do?message=${row.id}">
            <spring:message	code="forward" />
        </a>
    </display:column>

    <spring:message code="delete" var="deleteHeader"/>
    <display:column title="${deleteHeader}">
        <a href="message/delete.do?message=${row.id}" onclick="return confirm('<spring:message code="areyousure" /> ')">
            <spring:message	code="delete" />
        </a>
    </display:column>
</display:table>