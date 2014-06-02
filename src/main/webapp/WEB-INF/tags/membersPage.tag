<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@attribute name="members" type="java.util.List"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="members" />
<jsp:setProperty name="navigation" property="page" value="member" />

<t:genericPage>
	<jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
	<jsp:body>
      <div id="membersList">
        <h2>Members List</h2>
        <span class="searchWidget">
          <input type="text" name="q">
          <input type="submit" id="submit" name="btnsub" value="Member Search">
        </span>
        <div id="membersTable">
          <header>
            <h3>Name</h3>
            <h3>Member since</h3>
          </header>
          <div class="clear"></div>
          <c:forEach var="member" items="${members}">
          <a href="member.jsp?id=${member.id}">
	          <div class="memberListEntry">
	            <p>${member.name}</p>
	            <p><fmt:formatDate value="${member.joinedOn}" pattern="dd.MM.yyyy" /></p>
	          </div>
          </a>
          </c:forEach>
        </div>
        <div class="clear"></div>
      </div>

	</jsp:body>
</t:genericPage>