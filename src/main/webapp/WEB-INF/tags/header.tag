<%@tag language="java" pageEncoding="UTF-8"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />

<header>
	<div id="login" class="pipeList">
		<ul>
			<li><a href="login.jsp">Login</a></li>
			<li><a href="register.jsp">Register</a></li>
		</ul>
		<ul>
            <li><a href="UCP.jsp">User Control Panel</a></li>
        </ul>
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
