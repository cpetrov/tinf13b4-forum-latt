<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
	<c:if test="${pageContext.request.requestURI != '/login.jsp' and
	              pageContext.request.requestURI != '/register.jsp' and
	              pageContext.request.requestURI != '/forgotten.jsp' and
	              pageContext.request.requestURI != '/error.jsp' }">
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
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
	<div class="holderClean">
		<a href="index.jsp"><img src="img/logo.png" alt="Forum" /></a>
	</div>
</header>