<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" scope="request" />
<c:if test="${not empty param.content and not empty param.threadId and not empty param.userId }">
    <c:set target="${consumer}" property="threadId" value="${param.threadId}"></c:set>
    <c:set target="${consumer}" property="userId" value="${param.userId}"></c:set>
    <c:set target="${consumer}" property="post" value="${param.content}"></c:set>
</c:if>
<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" scope="request" />
<c:set target="${provider}" property="threadId" value="${param.id}" ></c:set>
<c:set target="${provider}" property="categoryId" value="${provider.thread.categoryId}" ></c:set>
<t:threadPage category="${provider.category}" 
              posts="${provider.posts}"
              thread="${provider.thread}">
    <jsp:attribute name="title">Page Title</jsp:attribute>
</t:threadPage>
