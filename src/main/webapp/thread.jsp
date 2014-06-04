<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />
<c:set target="${dummyProvider }" property="id" value="${param.id }"></c:set>
<t:threadPage category="${dummyProvider.categories[param.id]}" 
			  users="${dummyProvider.users}" 
			  posts="${dummyProvider.posts}"
			  thread="${dummyProvider.threads[param.id]}">
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:threadPage>