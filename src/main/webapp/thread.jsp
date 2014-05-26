<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />

<t:threadPage category="${dummyProvider.categories[0]}" 
			  members="${dummyProvider.members}" 
			  posts="${dummyProvider.posts}"
			  thread="${dummyProvider.threads[0]}">
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:threadPage>