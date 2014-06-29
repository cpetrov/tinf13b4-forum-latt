<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />
<c:set target="${provider }" property="categoryId" value="${param.id}"></c:set>
<t:categoryPage category="${provider.category}" 
                threads="${provider.threads}" 
                authors="${provider.users}">
    <jsp:attribute name="title">${provider.category.title}</jsp:attribute>
</t:categoryPage>
