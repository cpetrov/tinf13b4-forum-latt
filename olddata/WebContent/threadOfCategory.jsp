<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${request.getParameter(category_Title) }</title>
</head>
<body>
<jsp:useBean id="ThreadDB" class="tinf13b4.forum.content.Thread_DB">
	<c:set target="${ThreadDB }" property="categoryId" value="${request.getParameter(category_ID) }" />
	<c:forEach var="current" items="${ThreadDB.threads }">
		<div>
			<a href="thread.jsp?threadID=${current.id } }">${current.title }</a>
		</div>
	</c:forEach>
</jsp:useBean>
</body>
</html>