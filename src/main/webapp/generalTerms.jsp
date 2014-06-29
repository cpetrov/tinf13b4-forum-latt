<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:staticPage text="${settings.termsOfUse}" heading="${provider.heading}">
	<jsp:attribute name="title">General Terms</jsp:attribute>
</t:staticPage>