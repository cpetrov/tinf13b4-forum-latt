<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="controller" class="tinf13b4.forum.controller.ConfirmationKeyController">
	<c:set target="${controller }" property="confirmationKey" value="${param.confirmationkey }"></c:set>
</jsp:useBean>
<c:redirect url="login.jsp"></c:redirect>