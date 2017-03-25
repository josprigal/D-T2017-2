<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<p>
	<spring:message code="administrator.ratioOffersVersusRequest" />
	${ratioOffersVersusRequest}
</p>
<br>


<p>
	<spring:message code="administrator.avgOffersCustomer" />
	${avgOffersAndRequestCustomer}
</p>

<br>

<p>
	<spring:message code="administrator.avgApplicationsPerOffer" />
	${avgApplicationsPerOffer}
</p>
<br>

<p>
	<spring:message code="administrator.avgApplicationsPerRequest" />
	${avgApplicationsPerRequest}
</p>
<br>

<p>
	<spring:message code="administrator.avgCommensPerAdmin" />
	${avgCommensPerAdmin}
</p>
<br>

<p>
	<spring:message code="administrator.avgCommensPerCustomer" />
	${avgCommensPerCustomer}
</p>
<br>

<p>
	<spring:message code="administrator.avgCommensPerActor" />
	${avgCommensPerActor}
</p>
<br>

<p>
	<spring:message code="administrator.avgCommensPerOffer" />
	${avgCommensPerOffer}
</p>
<br>
<p>
	<spring:message code="administrator.avgCommensPerRequest" />
	${avgCommensPerRequest}
</p>
<br>
<p>
	<spring:message code="administrator.minMessagesSentPerActor" />
	${minMessagesSentPerActor}
</p>
<br>


<p>
	<spring:message code="administrator.maxMessagesSentPerActor" />
	${maxMessagesSentPerActor}
</p>
<br>

<p>
	<spring:message code="administrator.avgMessagesSentPerActor" />
	${avgMessagesSentPerActor}
</p>
<br>


<p>
	<spring:message code="administrator.minMessagesReceivedPerActor" />
	${minMessagesReceivedPerActor}
</p>
<br>


<p>
	<spring:message code="administrator.maxMessagesReceivedPerActor" />
	${maxMessagesReceivedPerActor}
</p>
<br>

<p>
	<spring:message code="administrator.avgMessagesReceivedPerActor" />
	${avgMessagesReceivedPerActor}
</p>
<br>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="customerMoreDenied" requestURI="${requestURI}"
	id="customerMoreDenied">
	<spring:message code="administrator.customerMoreDenied"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="customerMoreAccepted" requestURI="${requestURI}"
	id="customerMoreAccepted">
	<spring:message code="administrator.customerMoreAccepted"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="actorMoreThan10Percent" requestURI="${requestURI}"
	id="actorMoreThan10Percent">
	<spring:message code="administrator.actorMoreThan10Percent"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="actorHasMoreMessages" requestURI="${requestURI}"
	id="actorHasMoreMessages">
	<spring:message code="administrator.actorHasMoreMessages"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="actorSentMoreMessages" requestURI="${requestURI}"
	id="actorSentMoreMessages">
	<spring:message code="administrator.actorSentMoreMessages"
		var="name" />
	<display:column property="name" title="${name}" sortable="true" />
</display:table>

<br>
