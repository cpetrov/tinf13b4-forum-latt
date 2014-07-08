<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<c:set target="${navigation }" property="acpNavigation" value="boardmanagement"/>


<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
		<c:if test="${not empty param.id }">
			<c:set target="${settings}" property="categoryId" value="${param.id}"></c:set>
			<c:if test="${not empty param.title }">
				<c:set target="${settings}" property="categoryTitle" value="${param.title}"></c:set>
			</c:if>
			<c:if test="${not empty param.subtitle }">
				<c:set target="${settings}" property="categorySubtitle" value="${param.subtitle}"></c:set>
			</c:if>
			<c:if test="${not empty param.sortNumber }">
				<c:set target="${settings}" property="categorySortNumber" value="${param.sortNumber}"></c:set>
			</c:if>
			<c:set target="${settings}" property="updateCategory" value="true"></c:set>
		</c:if>
		<div class="admContent">
			<h1>Welcome</h1> Edit Categories
			<p>
			<div>
				<form method="POST">
					<table>
						<thead><tr>
							<td>ID</td>
							<td>Title</td>
							<td>Subtitel</td>
							<td>Sort number</td>
						</tr></thead>
						<tr>
							<td><select name="id">
								<c:forEach var="category" items="${settings.categories }">
									<option label="${category.id}" value="${category.id}">
								</c:forEach>
								</select>
							</td>
							<td><input style="margin: 5px" name="title"></td>
							<td><input style="margin: 5px" name="subtitle"></td>
							<td><input style="margin: 5px" name="sortNumber"></td>
						</tr>
					</table>
					<input type="submit">
				</form>
			</div>
			
			<table class="table userList">
				<thead><tr>
					<td>ID</td>
					<td>Title</td>
					<td>Subtitel</td>
					<td>Sort number</td>
				</tr></thead>
				<c:forEach var="category" items="${settings.categories }">
					<tr>
						<td>${category.id }</td>
						<td>${category.title }</td>
						<td>${category.subtitle }</td>
						<td>${category.orderNumber }</td>
					</tr>
				</c:forEach>
			</table>
			</p>
		</div>
	</jsp:body>
</t:genericPage>