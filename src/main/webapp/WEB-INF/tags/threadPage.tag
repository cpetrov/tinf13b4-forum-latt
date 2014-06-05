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
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="thread" />

<t:genericPage>
    <jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
    <jsp:body>
    <t:breadcrumbNav category="${category}" />
          <section>
          
        <header>
          <h2>${thread.title}</h2>
        </header>
        <div id="topic">
          <div class="answer">
            <div class="user">
              <div class="userPictureHolder"></div>
              <div class="name">
                <img src="./img/user16.png" alt="Username" /> <a href="user.jsp?userId=${users[0].id}">${users[0].name}</a>
              </div>
               <div class="posts">
                <img src="./img/bubbles16.png" alt="Posts" /> ${users[0].posts.size()} posts
              </div> 
            </div>
            <div class="answerBody">${thread.content} 
              <button>
                <img src="./img/quill.png" alt="Quote">
                <span>Quote</span>
              </button>
              <div class="postedOn">Posted on ${post.date }</div>
            </div>
            <div class="clear"></div>
          </div>
         
          <c:forEach var="post" items="${posts }">
          <div class="answer">
            <div class="user">
              <div class="userPictureHolder"></div>
              <div class="name">
                <img src="./img/user16.png" alt="Username" /> 
         		<a href="user.jsp?userId=${users[0].id}">${users[0].name}</a>
              </div>
              <div class="posts">
                <img src="./img/bubbles16.png" alt="Answers" /> ${users[0].posts.size()} posts
              </div> 
            </div>
            <div class="answerBody">
                ${post.content }
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
          <span>New Post</span>
        </button>
      </div>
      <div class="newPost">
        <textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>
      </div>
      <ckeditor:replace replace="editor1" basePath="/ckeditor/" />
    </jsp:body>
</t:genericPage>