<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<form method="POST">
				<c:if test="${!empty param.pageInprint}"><	
					<jsp:setProperty name="settings" property="pageInprint" value="${param.pageInprint}" />
				</c:if>
				<h1>Impressum</h1>
				<p>
					Hier können die Impressum der Seite bearbeitet werdenn
				</p>
				<p><textarea name="pageInprint" cols="100" rows="10">${settings.pageInprint}</textarea></p>
				<button type="submit" class="navButton">Speichern</button>
			</form>
		</div>
	</jsp:body>
</t:genericPage>