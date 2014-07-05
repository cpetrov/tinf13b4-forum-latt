<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="user" type="tinf13b4.forum.model.User"%>
<%@attribute name="thread" type="tinf13b4.forum.model.Thread"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:useBean id="settings" class="tinf13b4.forum.beans.SettingsBean" scope="request" />

<nav class="breadcrumb">
	<ul>
		<li><a href="index.jsp">${settings.pageName}</a></li>
		<c:choose>
			<c:when test="${navigation.category eq 'boards'}">
				<li><a href="category.jsp?id=${category.id}">${category.title}</a></li>
				<c:if test="${navigation.page eq 'thread' }">
                    <li><a href="thread.jsp?id=${thread.id}">${thread.title}</a></li>
                </c:if>
			</c:when>
			<c:when test="${navigation.category eq 'users'}">
				<li><a href="users.jsp">Users</a></li>
				<c:if test="${navigation.page eq 'user' }">
					<li><a href="user.jsp?id=${user.id}">${user.name}</a></li>
				</c:if>
			</c:when>
		</c:choose>
	</ul>
</nav>
