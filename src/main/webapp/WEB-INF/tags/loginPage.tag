<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="category" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header">
		<t:headerClean />
	</jsp:attribute>
	<jsp:body>
		<div id="inputBlock">
		<div id="inputHolder">
		<form method="post">
			<label for="username">Username</label>
			<input id="username" type="text" placeholder="Username" />
			<label for="password">Password</label>
			<input id="password" type="password" placeholder="Password" />
			<button type="submit">Login</button>
		</form>
		</div>
		</div>
	</jsp:body>
</t:genericPage>