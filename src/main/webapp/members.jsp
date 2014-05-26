<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />

<t:membersPage members="${dummyProvider.members}" >
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:membersPage>