<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>

<%@attribute name="title" fragment="true"%>
<%@attribute name="js" fragment="true"%>

<head>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/" />
	<meta charset="UTF-8">
	<title><jsp:invoke fragment="title" /></title>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="fonts/stylesheet.css">
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<jsp:invoke fragment="js" />
	<script src="./js/layout.js"></script>
	<script src="./js/main.js"></script>
</head>
