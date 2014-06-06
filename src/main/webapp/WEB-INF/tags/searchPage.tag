<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@attribute name="users" type="java.util.List"%>
<%@attribute name="categories" type="java.util.List"%>
<%@attribute name="threads" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="search" />
<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />
<c:set target="${dummyProvider }" property="threadId" value="${param.threadId }"></c:set>
<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header">
		<t:header />
	</jsp:attribute>
	<jsp:body>
	<section>
    <header>
	   <h2>Search Results</h2>
    </header>
	<c:choose>
	<c:when test="${categories.size() > 0}">
    <section>
	   <c:forEach var="category" items="${categories}">
	   <article>
        <img src="./img/bubbles.png" alt="Category">
            <div>
                <h4>
                    <a href="category.jsp?categoryId=${category.id}">${category.title}</a>
                </h4>
                <p>${category.subtitle}</p>
            </div>
       </article>
	   </c:forEach>
    </section>
	</c:when>
	<c:when test="${threads.size() > 0}">
    <c:forEach var="thread" items="${threads}">
        <article>
            <img src="./img/bubble.png" alt="Topic">
            <div>
                <h4>
                    <a href="thread.jsp?threadId=${thread.id}">${thread.title}</a> 
                </h4>
                <p>
                    <b>Author:</b> ${authors[i].name}
                </p>
            </div>
        </article>
    </c:forEach>
    </c:when>
    <c:when test="${users.size() > 0 }">
      <div id="usersList">
        <div id="usersTable">
          <header>
            <h3>Name</h3>
            <h3>User since</h3>
          </header>
          <div class="clear"></div>
          <c:forEach var="user" items="${users}">
          <a href="user.jsp?userId=${user.id}">
              <div class="userListEntry">
                <p>${user.name}</p>
                <p> <fmt:formatDate value="${user.joinedOn}" pattern="dd.MM.yyyy" /> </p>
              </div>
          </a>
          </c:forEach>
        </div>
        <div class="clear"></div>
      </div>
    </c:when>
	</c:choose>
	</section>
</jsp:body>
</t:genericPage>
