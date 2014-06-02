<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%@attribute name="category" type="tinf13b4.forum.model.Category"%>
<%@attribute name="thread" type="tinf13b4.forum.model.Thread"%>
<%@attribute name="members" type="java.util.List"%>
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
            <div class="member">
              <div class="memberPictureHolder"></div>
              <div class="name">
                <img src="./img/user16.png" alt="Username" /> <a href="member.jsp?id=${members[0].id}">${members[0].name}</a>
              </div>
              <div class="posts">
                <img src="./img/bubbles16.png" alt="Posts" /> ${members[0].posts} posts
              </div>
            </div>
            <div class="answerBody">${thread.content} 
              <button>
                <img src="./img/quill.png" alt="Quote">
                <span>Quote</span>
              </button>
              <div class="postedOn">Posted on 24.05 at 21:49</div>
            </div>
            <div class="clear"></div>
          </div>
         
          <c:forEach begin="0" var="i" end="${posts.size()-1}">
          <div class="answer">
            <div class="member">
              <div class="memberPictureHolder"></div>
              <div class="name">
                <img src="./img/user16.png" alt="Username" /> <a href="member.jsp?id=${members[i+1].id}">${members[i+1].name}</a>
              </div>
              <div class="posts">
                <img src="./img/bubbles16.png" alt="Answers" /> ${members[i+1].posts} posts
              </div>
            </div>
            <div class="answerBody">
              	${posts[i].content}
              <button>
                <img src="./img/quill.png">
                <span>Quote</span>
              </button>
              <div class="postedOn">Posted on 24.05 at 21:49</div>
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