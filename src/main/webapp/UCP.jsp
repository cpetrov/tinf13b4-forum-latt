<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<c:choose>
	<c:when test="${not empty userSession.userName }">
		<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
		<c:set target="${provider}" property="userName" value="${userSession.userName }"></c:set>
		<t:UCPPage user="${provider.user}" success="${success}" error="${error}">
		    <jsp:attribute name="title">User Control Panel</jsp:attribute>
		</t:UCPPage>
	</c:when>
	<c:otherwise>
		<c:redirect url="/index.jsp"></c:redirect>
	</c:otherwise>
</c:choose>
