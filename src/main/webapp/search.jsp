<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="dummyProvider" class="tinf13b4.forum.beans.DummyProviderBean" />

<t:searchPage categories="${dummyProvider.categories}" 
             users="${dummyProvider.users }"
             threads="${dummyProvider.threads }">
    <jsp:attribute name="title">Search</jsp:attribute>
</t:searchPage>

