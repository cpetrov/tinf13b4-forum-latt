<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:usersPage users="${provider.users}" >
	<jsp:attribute name="title">Users</jsp:attribute>
</t:usersPage>