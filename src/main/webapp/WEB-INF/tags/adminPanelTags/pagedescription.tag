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
				<c:if test="${!empty param.pageDescription}">
					<jsp:setProperty name="settings" property="pageDescription" value="${param.pageDescription}" />
				</c:if>
				<h1>Page Description</h1>
				<p>
					Edit the page description.
				</p>
				
				<p><textarea name="pageDescription" cols="100" rows="10">${settings.pageDescription}</textarea></p>
				<button type="submit" class="navButton">Save</button>
			</form>
		</div>
	</jsp:body>
</t:genericPage>