<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="user"></c:set>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<h1>Welcome</h1> User Management
			<p>
			<table class="table userList">
				<thead><tr>
					<td>User ID</td>
					<td>Username</td>
					<td>E-mail Address</td>
					<td>Picture</td>
					<td>Activation</td>
					<td>Action</td>
				</tr></thead>
				<c:forEach var="user" items="${settings.users }">
					<tr>
						<td>${user.id }</td>
						<td>${user.name }</td>
						<td>${user.mail }</td>
						<td>${user.picture }</td>
						<td>${user.confirmed }</td>
						<td><i class="editButton fa fa-pencil-square-o"></i></td>
					</tr>
				</c:forEach>
			</table>
			</p>
		</div>
	</jsp:body>
</t:genericPage>