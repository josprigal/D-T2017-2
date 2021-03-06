<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="register" />
	<spring:message code="as" />
	<spring:message code="customer" />
</h1>
<form:form action="customer/register.do" modelAttribute="customer"
	method="POST">

	<div class="form-group-1">
		<h2>
			<spring:message code="useraccountdata" />
		</h2>
		<form:label path="userAccount.username">
			<spring:message code="userAccount.username" />:
        </form:label>
		<form:input path="userAccount.username" />
		<form:errors cssClass="error" path="userAccount.username" />

		<form:label path="userAccount.password">
			<spring:message code="userAccount.password" />:
        </form:label>
		<form:password path="userAccount.password" />
		<form:errors cssClass="error" path="userAccount.password" />
		<br>
		<form:errors cssClass="error" path="email" />
	</div>
	<h2>
		<spring:message code="personaldata" />
	</h2>
	<div class="form-group-2">
		<form:label path="email">
			<spring:message code="email" />:
        </form:label>
		<form:input path="email" type="email" required="required" />
		<form:errors cssClass="error" path="email" />
		<br>
		<form:label path="name">
			<spring:message code="name" />:
        </form:label>
		<form:input path="name" required="required" />
		<form:errors cssClass="error" path="name" />
		<br>

		<form:label path="phone">
			<spring:message code="phone" />:
        </form:label>
		<form:input path="phone"
			pattern="((\+[0-9]{1,3})?\s*(\([0-9]{3}\))?\s*([a-zA-Z0-9\- ]{4,}))$" />
		<form:errors cssClass="error" path="phone" />
		<br>

	</div>
	<input name="register" type="submit"
		value="<spring:message code="submit" />" />
</form:form>