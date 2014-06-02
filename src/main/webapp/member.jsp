<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />

<t:memberPage threads="${dummyProvider.threads}" 
			  member="${dummyProvider.members[0]}" 
			  posts="${dummyProvider.posts}">
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:memberPage>