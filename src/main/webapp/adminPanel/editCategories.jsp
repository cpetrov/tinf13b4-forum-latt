<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	<link rel="icon" type="/image/png" href="favicon.png" />
		<link id="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
		<link id="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
		<link id="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" rel="stylesheet">
		<link href="css/style.css" type="text/css" rel="stylesheet">

		<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
		<script src="js/script.js" type="text/javascript"></script>

			<jsp:useBean id="adminPanelSettingsBean" class="tinf13b4.forum.beans.AdminPanelSettingsBean"></jsp:useBean>

		<title>Forenverwaltung</title>
	</head>
	<body>
		<div class="container">
			<div class="admPanel">
				<div class="admHeader">
					<a href="index.jsp"><img src="img/admpnl.png" class="admLogo"></a>
					<p>
						AdminControlPanel (ACP)
					</p>
				</div>
				<div class="admNav">
					<ul>
						<li class="expander">
							<i class="fa fa-bars"></i>
						</li>
						<li class="navIndex">
							<i class="fa fa-tachometer"></i>
							<span class="navPt">AdminPanel</span>
						</li>
						<li class="navGeneral">
							<i class="fa fa-codepen"></i>
							<span class="navPt">Allgemein</span>
						</li>
						<li class="navUser">
							<i class="fa fa-user"></i>
							<span class="navPt">Benutzerverwaltung</span>
						</li>
						<li class="navManage">
							<i class="fa fa-lock"></i>
							<span class="navPt">Forenverwaltung</span>
						</li>
						<li class="subNav navEditCategories">
							<span class="navPt">Foren bearbeiten</span>
						</li>
						<li class="navSettings">
							<i class="fa fa-cog"></i>
							<span class="navPt">Einstellungen</span>
						</li>
					</ul>
				</div>
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
						<c:forEach var="category" items="${adminPanelSettingsBean.categories }">
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
			</div>
		</div>
	</body>
</html>
