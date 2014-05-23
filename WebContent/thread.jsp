<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thread</title>
</head>
<body>
<jsp:useBean id="thread" class="tinf13b4.forum.content.Thread_DB">
	<% 
	thread.getAllThreadsInCategorie(Integer.parseInt(request.getParameter("Categorie_ID")));
	for(int i = 0; i < thread.getThreads().size(); i++) {
	%>
	<div>
		<a href="thread.jsp?threadID=<%= thread.getThreads().get(i).getId() %>"><%= thread.getThreads().get(i).getTitle() %></a>
	</div>
	<%} %>
</jsp:useBean>
</body>
</html>