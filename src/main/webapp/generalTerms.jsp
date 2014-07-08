<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:staticPage>
	<jsp:attribute name="title">General Terms</jsp:attribute>
	<jsp:attribute name="heading">Terms Of Use</jsp:attribute>
	<jsp:attribute name="content">${settings.termsOfUse}</jsp:attribute>
</t:staticPage>