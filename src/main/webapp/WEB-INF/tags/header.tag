<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<header>
	<div id="login" class="pipeList">
	<c:choose>
		<c:when test="${empty userSession.user.name }">
			<ul>
				<li><a href="login.jsp">Login</a></li>
				<li><a href="register.jsp">Register</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul>
				<li><a href="logout.jsp">Logout</a></li>
	            <li><a href="UCP.jsp">User Control Panel</a></li>
	            <c:if test="${userSession.user.permission == 2}">
	            	<li><a href="adminPanel/index.jsp">Admin Panel</a></li>
	            </c:if>
	        </ul>
		</c:otherwise>
	</c:choose>
	</div>
	<div class="holder">
		<a href="index.jsp"><img src="img/logo.png" alt="Forum" /></a>
		<span class="searchWidget">
		<form action="search.jsp" method="GET">
			<input type="text" name="q"> <input type="submit" id="submit" name="btnsub" value="Search">
		</form>
		</span>
	</div>
	<nav>
		<ul>
			<li class="${navigation.category eq 'boards' ? 'active' : ''}"><a href="index.jsp">Boards</a></li>
			<li class="${navigation.category eq 'users' ? 'active' : ''}"><a href="users.jsp">Users</a></li>
		</ul>
	</nav>
</header>
