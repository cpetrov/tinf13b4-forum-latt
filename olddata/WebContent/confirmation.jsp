<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:layout>	
    <jsp:attribute name="title">Bestätigung</jsp:attribute>
	<jsp:body>
		<jsp:useBean id="controller" class="tinf13b4.forum.register.ConfirmationKeyController">
			<c:set target="${controller }" property="confirmationKey" value="${param.confirmationkey }"></c:set>
	    </jsp:useBean> 	
	</jsp:body>
</t:layout>