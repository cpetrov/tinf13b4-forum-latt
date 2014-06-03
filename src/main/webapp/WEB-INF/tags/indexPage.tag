<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="categories" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="index" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
<section>
	<header>
		<h2>Categories</h2>
	</header>
	<section>
		<h3>First Category Group</h3> <%-- TODO implement category groups --%>
		<c:forEach var="category" items="${categories}">
		<article>
			<img src="./img/bubbles.png" alt="Category">
			<div>
				<h4>
					<a href="category.jsp?id=${category.id}">${category.title}</a>
				</h4>
				<p>${category.subtitle}</p>
			</div>
		</article>
		</c:forEach>
	</section>
</section>
</jsp:body>
</t:genericPage>
