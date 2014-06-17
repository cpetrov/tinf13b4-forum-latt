<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" scope="request" />
        <c:if test="${not empty param.name and not empty param.mail}">
            <c:set target="${consumer}" property="userName" value="${param.name}"></c:set>
            <c:set target="${consumer}" property="userPicturePath" value="picturePath"></c:set>
            <c:set target="${consumer}" property="userMail" value="${param.mail}"></c:set>
            <c:set target="${consumer}" property="user" value="1"></c:set> <%-- TODO get active user from session --%>
        </c:if>
<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
<c:set target="${provider}" property="userId" value="1"></c:set> <%-- TODO get active user from session --%>
<t:UCPPage user="${provider.user}" >
    <jsp:attribute name="title">Page Title</jsp:attribute>
</t:UCPPage>