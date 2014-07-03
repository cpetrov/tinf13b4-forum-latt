<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/adminPanelTags"%>

<%@attribute name="title" fragment="true"%>

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
		<div class="admContent">
			<h1>Willkommen</h1> Kategorien bearbeiten
			<p>
			<table class="table userList">
				<thead><tr>
					<td>ID</td>
					<td>Titel</td>
					<td>Untertitel</td>
					<td>Sortiernummer</td>
				</tr></thead>
				<c:forEach var="category" items="${settings.categories }">
					<tr>
						<td>${category.id }</td>
						<td>${category.title }</td>
						<td>${category.subtitle }</td>
						<td>${category.orderNumber }</td>
						<td><i class="editButton fa fa-pencil-square-o"></i></td>
					</tr>
				</c:forEach>
			</table>
			</p>
		</div>
	</jsp:body>
</t:genericPage>