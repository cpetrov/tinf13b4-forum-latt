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
<jsp:setProperty name="navigation" property="page" value="user" />

<t:genericPage>
    <jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
    <jsp:attribute name="header"><t:header /></jsp:attribute>
    <jsp:body>
    <t:breadcrumbNav user="${user}" />
      <div id="user">
        <div id="userBlock">
            <header>
                <h2>${user.name}</h2>
            </header>
            <div class="userPictureHolder"></div>
            <div id="posts">
                <img src="./img/quill16.png" alt=""> User has <b>${user.postCount}</b> posts
            </div>
            <div id="mailContact">
                <img src="./img/mail16.png" alt=""> <a href="mailto:${user.mail}">Mail</a>
            </div>
<%--            <div id="facebook">
                <img src="./img/facebook16.png" alt=""> <a href="http://www.facebook.com/${user.facebook}">Facebook</a>
            </div> 
--%>
        </div>
        <div id="topicBlock">
          <section>
          <c:choose>
          <c:when test="${posts.size() != 0 }">
            <h3>Latest Posts</h3>
            <section>
            <c:forEach step="1" begin="0" end="${posts.size() <= 5 ? posts.size()-1 : 4 }" var="i">
              <article>
                <img src="./img/bubble.png" alt="Posts">
                <div>
                  <a href="thread.jsp?id=${posts[i].threadId}"><p>${posts[i].content}</p></a>
                </div>
              </article>
            </c:forEach>
            </section>
          </c:when>
          <c:when test="${posts.size() == 0 }">
            <h3>${user.name } has no posts :(</h3>
          </c:when>
          </c:choose>
          <c:choose>
          <c:when test="${threads.size() != 0 }">
            <h3>Last Threads</h3>
            <section>
             <c:forEach step="1" begin="0" end="${threads.size() <= 5 ? threads.size()-1 : 4 }" var="i">
              <article>
                <img src="./img/bubbles.png" alt="Topics">
                <div>
                  <h4>
                    <a href="thread.jsp?id=${threads[i].id }">${threads[i].title}</a>
                  </h4>
                  <p>${threads[i].content.length() > 100 ? fn:substring(threads[i].content, 0, 100).concat('...') : threads[i].content }</p>
                </div>
              </article>
            </c:forEach>
            </section>
          </c:when>
          <c:when test="${threads.size() == 0 }">
            <h3>${user.name } has no threads :(</h3>
          </c:when>
          </c:choose>
          </section>
        </div>
        <div style="clear: both"></div>
      </div>
</jsp:body>
</t:genericPage>