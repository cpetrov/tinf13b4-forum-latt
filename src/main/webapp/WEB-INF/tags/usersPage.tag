<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@attribute name="users" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="users" />
<jsp:setProperty name="navigation" property="page" value="user" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:attribute name="header"><t:header /></jsp:attribute>
	<jsp:body>
	<section>
	<header><h2>Users List</h2></header>
      <div id="usersList">
        <div id="usersTable">
          <header>
            <h3>Name</h3>
            <h3>User since</h3>
          </header>
          <div class="clear"></div>
          <c:forEach var="user" items="${users}">
          <a href="user.jsp?id=${user.id}">
	          <div class="userListEntry">
	            <p>${user.name}</p>
	            <p><fmt:formatDate value="${user.joinedOn}" pattern="dd.MM.yyyy" /></p>
	          </div>
          </a>
          </c:forEach>
        </div>
        <div class="clear"></div>
      </div>
    </section>
	</jsp:body>
</t:genericPage>