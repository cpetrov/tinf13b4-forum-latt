<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="member" type="tinf13b4.forum.model.Member"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:useBean id="settings" class="tinf13b4.forum.beans.ForumSettingsBean" scope="request" />

<nav class="breadcrumb">
	<ul>
		<li><a href="index.jsp">${settings.forumName}</a></li>
		<c:choose>
			<c:when test="${navigation.category eq 'boards'}">
				<li><a href="category.jsp?id=${category.id}">${category.name}</a></li>
			</c:when>
			<c:when test="${navigation.category eq 'members'}">
				<li><a href="members.jsp">Members</a></li>
				<c:if test="${navigation.page eq 'member' }">
					<li><a href="member.jsp?id=${member.id}">${member.name}</a></li>
				</c:if>
			</c:when>
		</c:choose>
	</ul>
</nav>