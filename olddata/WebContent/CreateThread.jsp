<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${request.getParameter(category_Title) } - Neuer Thread</title>
</head>
<body>	
	<!-- Include jQuery, this can be omitted if it's already included -->
	<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	 
	<!-- Include the default theme -->
	<link rel="stylesheet" href="sceditor/minified/themes/default.min.css" type="text/css" media="all" />
	 
	<!-- Include the editors JS -->
	<script type="text/javascript" src="sceditor/minified/jquery.sceditor.xhtml.min.js"></script>
	<script>
	$(function() {
		    // Replace all textarea's
		    // with SCEditor
		    $("textarea").sceditor({
		        plugins: "xhtml",
			style: "sceditor/minified/jquery.sceditor.default.min.css"
		    });
		});
	</script>
<jsp:useBean id="thread" class="tinf13b4.forum.content.ThreadModel"/>
<jsp:useBean id="Thread_DB" class="tinf13b4.forum.content.Thread_DB"/>
	<div>
	<form method="post" action="">
		<div>
			<textarea style="width: 600px; height: 300px; display: none;"></textarea>
		</div>
		<div>
			<input type="submit" value="Erstellen" onclick="${response.sendRedirect(redirectUrl) }"/>
		</div>
	</form>
	</div>
</body>
</html>