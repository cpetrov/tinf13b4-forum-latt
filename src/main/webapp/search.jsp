<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:searchPage categories="${provider.categories}" 
             users="${provider.users }"
             threads="${provider.threads }">
    <jsp:attribute name="title">Search</jsp:attribute>
</t:searchPage>

