<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="header" fragment="true"%>
<%@attribute name="js" fragment="true"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="userSession" class="tinf13b4.forum.beans.SessionBean" scope="session" />
<jsp:useBean id="settings" class="tinf13b4.forum.beans.SettingsBean" scope="request"/>
<jsp:setProperty name="settings" property="forumName" value="Forum Name" />

<c:if test="${pageContext.session['new']}">  
	<c:set target="${settings }" property="hitCount" value="1"></c:set>
</c:if>

<c:if test="${settings.serviceMode }">
	<c:redirect url="/maintance.jsp"></c:redirect>
</c:if>

<!DOCTYPE html>
<html>
<t:head>
	<jsp:attribute name="title">
		<jsp:invoke fragment="title" />
	</jsp:attribute>
	<jsp:attribute name="js">
		<jsp:invoke fragment="js" />
	</jsp:attribute>
</t:head>
<body>
	<div id="wrapper">
		<jsp:invoke fragment="header" />
		<div id="content">

			<jsp:doBody />
			<div id="push"></div>
		</div>
	</div>
	<t:footer />
</body>
</html>