<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="adminPanelSettingsBean" class="tinf13b4.forum.beans.AdminPanelSettingsBean"/>
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

		<title>Einstellungen</title>
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
						<li class="subNav navUserManage">
							<span class="navPt">Benutzer</span>
						</li>
						<li class="navManage">
							<i class="fa fa-lock"></i>
							<span class="navPt">Forenverwaltung</span>
						</li>
						<li class="navSettings">
							<i class="fa fa-cog"></i>
							<span class="navPt">Einstellungen</span>
						</li>
					</ul>
				</div>
				
				<p>Problem mit UserTabelle!!</p>
				
				<div class="admContent">
					<h1>Willkommen</h1> Benutzerverwaltung
				</div>
			</div>
		</div>
	</body>
</html>