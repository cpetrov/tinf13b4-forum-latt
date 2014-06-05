<%@tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="title" fragment="true"%>
<%@attribute name="js" fragment="true"%>

<head>
	<meta charset="UTF-8">
	<title><jsp:invoke fragment="title" /></title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="fonts/stylesheet.css">
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<jsp:invoke fragment="js" />
	<script src="./js/layout.js"></script>
	<script src="./js/main.js"></script>
</head>
