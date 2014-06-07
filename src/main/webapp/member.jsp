<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:memberPage threads="${provider.threads}" 
			  member="${provider.members[0]}" 
			  posts="${provider.posts}">
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:memberPage>