<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<c:choose>
	<c:when test="${not empty userSession.userName }">
		<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" scope="request" />
		<c:if test="${not empty param.picturePath}">
			<c:set target="${consumer}" property="userPicturePath" value="picturePath"></c:set>
		</c:if>
		<c:if test="${not empty param.mail}">
			<c:set target="${consumer}" property="userMail" value="${param.mail}"></c:set>
		</c:if>
		<c:if test="${not empty param.picturePath or not empty param.mail }">
			<c:set target="${consumer}" property="user" value="${userSession.userId }"></c:set>
			<c:set var="success" value="true"></c:set>
		</c:if>
		<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
		<c:set target="${provider}" property="userName" value="${userSession.userName }"></c:set>
		<t:UCPPage user="${provider.user}" success="${success}" >
		    <jsp:attribute name="title">User Control Panel</jsp:attribute>
		</t:UCPPage>
	</c:when>
	<c:otherwise>
		<c:redirect url="/index.jsp"></c:redirect>
	</c:otherwise>
</c:choose>
