<%@attribute name="title" fragment="true" %>
<%@attribute name="css" fragment="true" %>
<%@attribute name="js" fragment="true" %>

<!doctype html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			<jsp:invoke fragment="title"/>
		</title>
		<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
		<jsp:invoke fragment="css"/>
	</head>
	<body>
		<jsp:doBody/>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
		<jsp:invoke fragment="js"/>
	</body>
</html>