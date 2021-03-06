<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>


<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
                    <li><a href="administrator/changeBanner.do"><spring:message code="master.page.administrator.changeBanner" /></a></li>
                    <li><a href="administrator/banrequest.do"><spring:message code="master.page.administrator.banrequest" /></a></li>
					<li><a href="administrator/comment/list.do"><spring:message code="master.page.administrator.bancomments" /></a></li>
					<li><a href="actor/administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>

				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>

				<ul>
					<li class="arrow"></li>
					<li><a href="actor/customer/offer/list.do"><spring:message code="master.page.customer.offer" /></a></li>
					<li><a href="actor/customer/request/list.do"><spring:message code="master.page.customer.request" /></a></li>
					<li><a href="application/list.do"><spring:message code="applicationsreceived" /></a></li>
					<li><a href="application/sended.do"><spring:message code="applicationssended" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>

        <security:authorize access="isAuthenticated()">
            <li>
                <a class="fNiv">
                    <spring:message code="messages" />
                </a>
                <ul>
                    <li><a href="message/list.do"><spring:message code="messagelist"/> </a> </li>
                </ul>
            </li>

        </security:authorize>
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/list.do"><spring:message code="actors"/> </a> </li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>

		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

