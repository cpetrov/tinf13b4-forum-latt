<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suche</title>
</head>
<body>
	<jsp:useBean id="searchBean" class="tinf13b4.forum.search.SearchBean" />

	<form method="get" action="Search.jsp">
		<jsp:setProperty name="searchBean" property="searchObject" param="searchObject" />
		<jsp:setProperty name="searchBean" property="destination" param="destination" />

		<table>
			<tr>
				<td>Search for</td>
				<td></td>
				<td>Found</td>
			</tr>
			<tr>
				<td>
					<Input type="text" name="searchObject" value="${searchBean.searchObject}" />
				</td>
				<td>
					<label id="destination" for="search_type">Search in:</label> 
					<select id="search_type" class="primary" onchange="change_type()" name="destination">
						<option class="" value=1>Threads</option>
						<option class="" value=2>User</option>
						<option class="" value=3>Categories</option>
					</select>
				</td>
				<td>
					<c:forEach var="current" items="${searchBean.threads}">
						<c:out value="${current.content }"></c:out>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>
					<button name="startsearch" type="submit">Search</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>