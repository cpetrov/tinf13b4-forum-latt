<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>title</title>
</head>
<body>
<jsp:useBean id="category" class="tinf13b4.forum.content.CategoryController"/>
<c:set target="${category }" property="categorys" value="null"/>
	<c:forEach var="current" items="${category.categorys }">
		<div>
			<a href="thread.jsp?threadID=${current.id } }">${current.title }</a>
			<p style="text-indent:10px;">${current.subtitle }</p>
		</div>
	</c:forEach>
</body>
</html>