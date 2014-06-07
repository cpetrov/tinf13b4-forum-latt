<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" scope="request" />
<c:set target="${dummyProvider}" property="threadId" value="${param.id}" ></c:set>
<c:set target="${dummyProvider}" property="categoryId" value="${dummyProvider.thread.categoryId}" ></c:set>
<t:threadPage category="${dummyProvider.category}" 
              posts="${dummyProvider.posts}"
              thread="${dummyProvider.thread}">
    <jsp:attribute name="title">Page Title</jsp:attribute>
</t:threadPage>
