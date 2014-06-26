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
<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
<jsp:useBean id="search" class="tinf13b4.forum.beans.SearchBean" />
<c:set target="${search}" property="searchObject" value="${param.q}"></c:set>
<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header">
		<t:header />
	</jsp:attribute>
	<jsp:body>
	<section class="search">
    <header>
	   <h2>Search Results</h2>
    </header>
    <c:set target="${search}" property="destination" value="3"></c:set>
	<c:if test="${search.categories.size() > 0}">
	<h3>Results in Categories</h3>
    <section>
	   <c:forEach var="category" items="${search.categories}">
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
	</c:if>
	
	<c:set target="${search}" property="destination" value="1"></c:set>
	<c:if test="${search.threads.size() > 0}">
	<h3>Results in Threads</h3>
	<section>
    <c:forEach var="thread" items="${search.threads}">
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
    </c:if>
    <c:set target="${search}" property="destination" value="2"></c:set>
    <c:if test="${search.users.size() > 0 }">
      <div id="usersList">
        <h3>Results in Users</h3>
        <div id="usersTable">
          <header>
            <h3>Name</h3>
            <h3>User since</h3>
          </header>
          <div class="clear"></div>
          <c:forEach var="user" items="${search.users}">
          <a href="user.jsp?id=${user.id}">
              <div class="userListEntry">
                <p>${user.name}</p>
                <p> <fmt:formatDate value="${user.joinedOn}" pattern="dd.MM.yyyy" /> </p>
              </div>
          </a>
          </c:forEach>
        </div>
        <div class="clear"></div>
      </div>
    </c:if>
    <c:if test="${search.categories.size() == 0 and search.users.size() == 0 and search.threads.size() == 0}">
        <h3>No search results :(</h3>
    </c:if>
	</section>
</jsp:body>
</t:genericPage>
