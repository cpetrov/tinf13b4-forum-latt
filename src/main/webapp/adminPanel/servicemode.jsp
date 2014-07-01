<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="settings" class="tinf13b4.forum.beans.SettingsBean"/>
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
						<li class="navManage">
							<i class="fa fa-lock"></i>
							<span class="navPt">Forenverwaltung</span>
						</li>
						<li class="navSettings">
							<i class="fa fa-cog"></i>
							<span class="navPt">Einstellungen</span>
						</li>
						<li class="subNav navServiceMode">
							<span class="navPt">Wartungsmodus</span>
						</li>
					</ul>
				</div>
				<div class="admContent">

				<c:if test="${not empty param.serviceReason }">
					<c:set target="${settings}" property="serviceReason" value="${param.serviceReason}"></c:set>
					<c:set target="${settings}" property="serviceMode" value="${not empty param.onoffswitch? true : false}"></c:set>
				</c:if>		
				<form method="POST">
					<h1>Wartungsmodus</h1>
						<p>
							Wartungsmodus bearbeiten:
						</p>
					<div class="onoffswitch">
						<input type="checkbox" name="onoffswitch" class="onoffswitch-checkbox" id="myonoffswitch"
							${settings.serviceMode ? 'checked' : ''} />
						<label class="onoffswitch-label" for="myonoffswitch"> 
							<span class="onoffswitch-inner"></span> <span class="onoffswitch-switch"></span>
						</label>
					</div>
					<p>
						<textarea id="service_reason" name="serviceReason" cols="100" rows="10" disabled="${!settings.serviceMode}">
						${settings.serviceReason}
						</textarea>
					</p>
	
					<button id="save" type="submit" class="navButton">Speichern</button>
				</form>			
				</div>
			</div>
		</div>
	<script>
		$(function(){
			$("#myonoffswitch").on("click", function(){
				 var checked = $(this).prop("checked");
				 
				 $("#service_reason").prop("disabled", !checked);
			});
		});
		
		$(function(){
			$("#save").on("click", function(){
				$("#service_reason").prop("disabled", false);
			});
		});
	</script>
	</body>
</html>
