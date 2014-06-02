<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<jsp:useBean id="Thread" class="tinf13b4.forum.content.ThreadModel"/>
	<c:set property="title" value="${request.getParameter(title) }"></c:set>
	<c:set property="content" value="${request.getParameter(content) }"></c:set>
	<c:set property="user_ID" value="${request.getParameter(user_ID) }"></c:set>
	<c:set property="title" value="${request.getParameter(title) }"></c:set>
	<jsp:useBean id="ThreadDB" class="tinf13b4.forum.content.Thread_DB"/>
	<c:set property="thread" value="Thread"></c:set>
	
	
	<c:redirect url="thread.jsp?threadID=${thread.id }"></c:redirect>
</body>
</html>