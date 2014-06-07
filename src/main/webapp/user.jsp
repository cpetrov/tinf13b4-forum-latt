<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
<c:set target="${provider }" property="userId" value="${param.id}"></c:set>
<t:userPage user="${provider.user}" 
              threads="${provider.threads}" 
			  posts="${provider.posts}">
	<jsp:attribute name="title">Page Title</jsp:attribute>
</t:userPage>