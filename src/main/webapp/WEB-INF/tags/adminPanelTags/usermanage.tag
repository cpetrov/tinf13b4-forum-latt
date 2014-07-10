<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="user"></c:set>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:body>
		<c:if test="${not empty param.id }">
			<c:set target="${settings}" property="userId" value="${param.id}"></c:set>
			<c:if test="${not empty param.name }">
				<c:set target="${settings}" property="userName" value="${param.name}"></c:set>
			</c:if>
			<c:if test="${not empty param.mail }">
				<c:set target="${settings}" property="userMail" value="${param.mail}"></c:set>
			</c:if>
			<c:if test="${not empty param.picture }">
				<c:set target="${settings}" property="userPicture" value="${param.picture}"></c:set>
			</c:if>
			<c:set target="${settings}" property="userConfirmed" value="${param.confirmed eq 'on' ? true : false}"></c:set>
			<c:set target="${settings}" property="updateUser" value="true"></c:set>
		</c:if>
		<div class="admContent">
			<h1>Welcome</h1> User Management
			<p>
			<div>
				<form method="POST">
					<table>
						<thead><tr>
							<td>User ID</td>
							<td>Username</td>
							<td>E-mail Address</td>
							<td>Picture</td>
							<td>Activation</td>
						</tr></thead>
						<tr>
							<td><select name="id">
								<c:forEach var="user" items="${settings.users }">
									<option label="${user.id}" value="${user.id}">${user.id}</option>
								</c:forEach>
								</select>
							</td>
							<td><input style="margin: 5px" name="name"></td>
							<td><input style="margin: 5px" name="mail"></td>
							<td><input style="margin: 5px" name="picture"></td>
							<td><input type="checkbox" style="margin: 5px" name="confirmed" checked="checked"></td>
						</tr>
					</table>
					<input type="submit">
				</form>
			</div>
			
			<table class="table userList">
				<thead><tr>
					<td>User ID</td>
					<td>Username</td>
					<td>E-mail Address</td>
					<td>Picture</td>
					<td>Activation</td>
				</tr></thead>
				<c:forEach var="user" items="${settings.users }">
					<tr>
						<td>${user.id }</td>
						<td>${user.name }</td>
						<td>${user.mail }</td>
						<td>${user.picture }</td>
						<td>${user.confirmed }</td>
					</tr>
				</c:forEach>
			</table>
			</p>
		</div>
	</jsp:body>
</t:genericPage>
