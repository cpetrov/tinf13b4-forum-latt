<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thread</title>
</head>
<body>
<jsp:useBean id="database" class="tinf13b4.forum.database.SqlRequests">
	<% 
	database.getAllThreads();
	while(database.getResultSet().next()) { 
	%> 
		<a href="thread.jsp?threadid=  ${database.resultSet.getInt("Thread_ID") }> ${database.resultSet.getString("Title") }</a>
	<% } %>
</jsp:useBean>
</body>
</html>