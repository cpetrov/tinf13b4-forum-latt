<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="authors" type="java.util.List"%>
<%@attribute name="threads" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="settings" class="tinf13b4.forum.beans.ForumSettingsBean" scope="request" />
<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="category" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:body>
<t:breadcrumbNav category="${category}" />
<section>
	<header>
		<h2>${category.name}</h2>
	</header>
	<section>
	<c:forEach var="i" begin="0" end="${threads.size()-1}">
		<article>
			<img src="./img/bubble.png" alt="Topic">
			<div>
				<h4>
					<a href="thread.jsp?id=${threads[i].id}">${threads[i].title}</a> 
				</h4>
				<p>
					<b>Author:</b> ${authors[i].name}
				</p>
			</div>
		</article>
	</c:forEach>
	</section>
	<div>
		<button>
			<a href="topic.jsp"><img src="./img/quill.png"><span>New Topic</span></a>
		</button>
	</div>
</section>
</jsp:body>
</t:genericPage>