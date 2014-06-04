<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />
<c:set target="${dummyProvider }" property="threadId" value="${param.threadId }"></c:set>
<t:threadPage category="${dummyProvider.category}" 
			  users="${dummyProvider.users}" 
			  posts="${dummyProvider.posts}"
			  thread="${dummyProvider.threads[param.threadId]}">
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:threadPage>