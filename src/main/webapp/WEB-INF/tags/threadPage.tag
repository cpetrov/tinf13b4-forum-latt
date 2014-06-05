<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="thread" type="tinf13b4.forum.model.Thread"%>
<%@attribute name="users" type="java.util.List"%>
<%@attribute name="posts" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" scope="request" />

<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="thread" />
<c:set target="${provider}" property="userId" value="${thread.user.id}"></c:set>

<t:genericPage>
    <jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
    <jsp:attribute name="js"><script src="./js/ckUtil.js"></script></jsp:attribute>
    <jsp:attribute name="header"><t:header /></jsp:attribute>
    <jsp:body>
    <c:if test="${not empty param.content and not empty param.threadId and not empty param.userId }">
    <script>
        window.forum.posted = true;
    </script>
    </c:if>
    <t:breadcrumbNav category="${category}" thread="${thread}"/>
          <section>
        <header>
          <h2>${thread.title}</h2>
        </header>
        <div id="topic">
          <div class="answer">
            <div class="user">
              <div class="userPictureHolder"></div>
              <div class="name">
                <img src="./img/user16.png" alt="Username" /> <a href="user.jsp?id=${thread.user.id}">${thread.user.name}</a>
              </div>
               <div class="posts">
                <img src="./img/bubbles16.png" alt="Posts" /> ${thread.user.postCount} posts
              </div> 
            </div>
            <div class="answerBody">
            <div class="content"><p>${thread.content }</p></div>
              <button onclick="addTextToEditor(this)">
                <img src="./img/quill.png" alt="Quote">
                <span>Quote</span>
              </button>
              <div class="postedOn">Posted on ${thread.date }</div>
            </div>
            <div class="clear"></div>
          </div>
         
          <c:forEach var="post" items="${posts }">
          <div class="answer">
            <div class="user">
              <div class="userPictureHolder"></div>
              <div class="name">
                <img src="./img/user16.png" alt="Username" /> 
         		<a href="user.jsp?id=${post.user.id}">${post.user.name}</a>
              </div>
              <div class="posts">
                <img src="./img/bubbles16.png" alt="Answers" /> ${post.user.postCount} posts
              </div> 
            </div>
            <div class="answerBody">
              <div class="content"><p>${post.content }</p></div>
              <button>
                <img src="./img/quill.png">
                <span>Quote</span>
              </button>
              <div class="postedOn">Posted on ${post.date }</div>
            </div>
            <div class="clear"></div>
          </div>
        </c:forEach>
        </div>
      </section>
      <div>
        <button class="newPost">
          <img src="./img/quill.png">
          <span>New Post</span>
        </button>
      </div>
      <form method="POST">
	      <div class="newPost">
	        <textarea cols="80" id="editor" name="content" rows="10"></textarea>
	        <input type="hidden" name="userId" value="${thread.user.id}">
	        <input type="hidden" name="threadId" value="${thread.id}">
	        <button type="submit">Post</button>
	      </div>
      </form>
      <ckeditor:replace replace="editor" basePath="/ckeditor/" />
    </jsp:body>
</t:genericPage>
