<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<jsp:useBean id="ThreadDB" class="tinf13b4.forum.content.Thread_DB"/>
<c:set target="${ThreadDB }" property="threadId" value="${request.getParameter(threadID) }"></c:set>

<title>${ThreadDB.thread.title }</title>
</head>
<body>
	<form method="post" action="">
		<div id="thread">
			<!-- Threaddata -->
		</div>
		<div id="posts">
			<!-- get posts for thread and post them in -->
		</div>
		<div id="answer">
		<%@ include file="sceditor\editor.html" %>
		</div>
	</form>
</body>
</html>