<%@ tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://ckeditor.com" prefix="ckeditor" %>

<%@attribute name="title" fragment="true"%>

<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" scope="request" />
<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:setProperty name="navigation" property="category" value="boards" />
<jsp:setProperty name="navigation" property="page" value="newThread" />

<t:genericPage>
    <jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
    <jsp:attribute name="js"><script src="./js/ckUtil.js"></script></jsp:attribute>
    <jsp:attribute name="header">
        <t:header />
    </jsp:attribute>
    <jsp:body>
    <c:choose>
	    <c:when test="${not empty param.content and not empty param.title and not empty param.categoryId}">
	        <c:set target="${consumer}" property="threadTitle" value="${param.title}"></c:set>
		    <c:set target="${consumer}" property="threadUserId" value="${userSession.user.id }"></c:set>
		    <c:set target="${consumer}" property="threadCategoryId" value="${param.categoryId}"></c:set>
		    <c:set target="${consumer}" property="threadReadOnly" value="false" /> <%--TODO implement readOnly --%>
		    <c:set target="${consumer}" property="thread" value="${param.content}"></c:set>
		    <c:redirect url="thread.jsp?id=${consumer.lastInsertId }"/>
	    </c:when>
	    <c:when test="${not empty param.content and not empty param.title and empty param.categoryId}">
	       <div class="alert">Something went wrong :(</div>
	    </c:when>
	    <c:when test="${not empty param.content and empty param.title and not empty param.categoryId}">
	       <div class="alert">Title must not be empty.</div>
	    </c:when>
	    <c:when test="${empty param.content and empty param.title and not empty param.categoryId}">
           <div class="alert">Title and content must not be empty.</div>
        </c:when>
	    <c:when test="${empty param.content and not empty param.title and not empty param.categoryId}">
	       <div class="alert">Content must not be empty.</div>
	    </c:when>
    </c:choose>
	    <section>
	        <header><h2>New Thread</h2></header>
	    </section>
	    <form method="POST">
			<div class="newThread">
			    <div class="inputHolder">
				    <label for="subject">Title</label>
				    <input id="subject" name="title" type="text" />
				    <label for="editor">Content</label>
				    <textarea cols="80" id="editor" name="content" rows="10"></textarea>
				    <button type="submit">Post</button>
			    </div>
		    </div>
	    </form>
	    <ckeditor:replace replace="editor" basePath="/ckeditor/" />
    </jsp:body>
</t:genericPage>