<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thread</title>
</head>
<body>
	<jsp:useBean id="database" type="database.SqlInjection" class="database.SqlInjection">
		<% 
		SqlInjection mSqlInjection = new SqlInjection();
		int countThreads = mSqlInjection.countThreads();
		for(int i = 0; i < countThreads; i++) {%>
	 	
	 	<a href="thread.jsp?threadid=<%= resultSet.getInt("Thread_ID") %>"><%= resultSet.getString("Thread_Title") %></a>
		
		<% } %>
	</jsp:useBean>
</body>
</html>