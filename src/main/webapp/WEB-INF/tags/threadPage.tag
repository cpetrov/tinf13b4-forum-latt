<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="thread" type="tinf13b4.forum.model.Thread"%>
<%@attribute name="users" type="java.util.List"%>
<%@attribute name="posts" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="thread" />
<c:set target="${provider}" property="userId" value="${thread.user.id}"></c:set>

<t:genericPage>
    <jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
    <jsp:attribute name="header"><t:header /></jsp:attribute>
    <jsp:body>
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
            <p>${thread.content }</p>
              <button>
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
              <p>${post.content }</p>
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
        <button class="newTopic">
          <img src="./img/quill.png">
          <span>New Topic</span>
        </button>
      </div>
    </jsp:body>
</t:genericPage>
