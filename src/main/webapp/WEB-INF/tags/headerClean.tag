<%@tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
	<c:if test="${pageContext.request.requestURI not eq '/login.jsp'}">
		<c:if test="${pageContext.request.requestURI not eq '/register.jsp'}">
			<c:if test="${pageContext.request.requestURI not eq '/forgotten.jsp'}">
				<div id="login" class="pipeList">
					<c:choose>
						<c:when test="${empty userSession.userName }">
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
		</c:if>
	</c:if>
	<div class="holderClean">
		<a href="index.jsp"><img src="img/logo.png" alt="Forum" /></a>
	</div>
</header>