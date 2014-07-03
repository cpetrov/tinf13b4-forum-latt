<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="authors" type="java.util.List"%>
<%@attribute name="threads" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="settings" class="tinf13b4.forum.beans.SettingsBean" scope="request" />
<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="category" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
<t:breadcrumbNav category="${category}" />
<section>
	<header>
		<h2>${category.title}</h2>
	</header>
	<section>
	<c:forEach  var="thread" items="${threads}">
		<article>
			<img src="./img/bubble.png" alt="Topic">
			<div>
				<h4>
					<a href="thread.jsp?id=${thread.id}">${thread.title}</a> 
				</h4>
				<p>
					<b>Author:</b> ${thread.user.name}
				</p>
			</div>
		</article>
	</c:forEach>
	</section>
	<c:if test="${not empty userSession.user.name }">
		<div>
			<button>
				<a href="newThread.jsp?categoryId=${category.id}"><img src="./img/quill.png"><span>New Topic</span></a>
			</button>
		</div>
	</c:if>
</section>
</jsp:body>
</t:genericPage>
