<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="header" fragment="true"%>
<%@attribute name="js" fragment="true"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="settings" class="tinf13b4.forum.beans.SettingsBean" scope="request"/>
<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:if test="${empty userSession or userSession.user.permission != 2 }">
	<c:redirect url="../index.jsp"></c:redirect>
</c:if>

<!DOCTYPE html>
<html>
<t:head>
	<jsp:attribute name="title">
		Admin Panel - <jsp:invoke fragment="title" />
	</jsp:attribute>
	<jsp:attribute name="js">
		<jsp:invoke fragment="js" />
	</jsp:attribute>
</t:head>

<body>
	<t:header />
	<div class="container">
		<div class="admPanel">
			<t:navigation />
			<div class="admContent">
				<h1>Welcome</h1> Forum Management
			</div>
		</div>
	</div>
	<jsp:doBody />
</body>
</html>