<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="threads" type="java.util.List"%>
<%@attribute name="posts" type="java.util.List"%>
<%@attribute name="user" type="tinf13b4.forum.model.User"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="users" />
<jsp:setProperty name="navigation" property="page" value="users" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:body>
	<t:breadcrumbNav user="${user}" />
      <div id="user">
		<div id="userBlock">
			<header>
				<h2>${user.name}</h2>
			</header>
			<div class="userPictureHolder"></div>
			<div id="posts">
				<img src="./img/quill16.png" alt=""> User has <b>${user.posts}</b> posts
			</div>
			<div id="mailContact">
				<img src="./img/mail16.png" alt=""> <a href="mailto:${user.email}">Mail</a>
			</div>
<%-- 			<div id="facebook">
				<img src="./img/facebook16.png" alt=""> <a href="http://www.facebook.com/${user.facebook}">Facebook</a>
			</div> 
--%>
		</div>
        <div id="topicBlock">
          <section>
            <h3>Latest Posts</h3>
            <section>
            <c:forEach var="post" items="${posts}">
              <article>
                <img src="./img/bubble.png" alt="Category">
                <div>
                  <h4>
					<a href="thread.jsp?threadId=${post.threadId }">${post.title}</a>
                  </h4>
                  <p>${post.content}</p>
                </div>
              </article>
            </c:forEach>
            </section>
            <h3>Last Topics</h3>
            <section>
              <c:forEach var="thread" items="${threads}">
              <article>
                <img src="./img/bubble.png" alt="Category">
                <div>
                  <h4>
					<a href="#">${thread.title}</a>
                  </h4>
                  <p>${thread.content.length() > 100 ? fn:substring(thread.content, 0, 100).concat('...') : thread.content }</p>
                </div>
              </article>
            </c:forEach>
            </section>
          </section>
        </div>
        <div style="clear: both"></div>
      </div>
</jsp:body>
</t:genericPage>