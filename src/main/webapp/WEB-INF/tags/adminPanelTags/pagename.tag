<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="general"></c:set>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<form method="POST">
				<c:if test="${!empty param.pageName}">
					<jsp:setProperty name="settings" property="pageDescription" value="${param.pageName}" />
				</c:if>
				<h1>Pagename</h1>
				<p>
					Here you can change the Pagename of your Forum
				</p>
				
				<p><textarea name="pageName" cols="100" rows="10">${settings.pageName}</textarea></p>
				<button type="submit" class="navButton">Speichern</button>
			</form>
		</div>
	</jsp:body>
</t:genericPage>