<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="provider" class="tinf13b4.forum.beans.ProviderBean" />

<t:staticPage>
	<jsp:attribute name="title">Usage Agreement</jsp:attribute>
	<jsp:attribute name="heading">Usage Agreement</jsp:attribute>
	<jsp:attribute name="content">${settings.termsOfUse}</jsp:attribute>
</t:staticPage>