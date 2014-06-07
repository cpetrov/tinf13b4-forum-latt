<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:membersPage members="${provider.members}" >
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:membersPage>