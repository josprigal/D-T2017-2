

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<a href="message/list.do">
    <spring:message	code="messagelist" />
</a>

    <form:form modelAttribute="message1" method="POST">
        <acme:textbox path="receiver" code="sender" />
        <acme:textbox path="title" code="title" />
        <acme:textarea path="text" code="text" />
        <acme:textbox path="attachments" code="attachments" />

        <acme:submit name="send" code="send" />

    </form:form>