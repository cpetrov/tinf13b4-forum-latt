<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="before" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="js" fragment="true" %>

<jsp:useBean id="navs" class="tinf13b4.forum.beans.NavBean" scope="application" />
<jsp:useBean id="view" class="tinf13b4.forum.beans.ViewBean" scope="session" />
<jsp:invoke fragment="before"/>

<!doctype html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			<jsp:invoke fragment="title"/>
		</title>
		<link rel="icon" type="image/png" href="favicon.png" />
		<link id="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
		<link href="css/register/style.css" rel="stylesheet">
		<jsp:invoke fragment="css"/>
	</head>
	<body>
		<div class="container">
			<div class="page-header">
				<ul class="nav nav-pills pull-right">
					<c:forEach var="nav" items="${navs.navs}">
						<li class="${navs.getClassIfActive(nav.name, view.name)}">
							<a href="${nav.href}">${nav.name}</a>
						</li>
					</c:forEach>
				</ul>
				<h1>
					<span class="glyphicon glyphicon-send"></span>
					Post It
				</h1>
			</div>
			<jsp:doBody/>
		</div>
		<div class="btn-group dropup style-chooser">
			<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
				<span class="caret"></span>
			</button>
			<ul class="dropdown-menu" ng-app="StyleChooserApp" ng-controller="StyleChooserController">
			</ul>
		</div>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
		<jsp:invoke fragment="js"/>
	</body>
</html>