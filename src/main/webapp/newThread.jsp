<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" />

<t:newThread>
    <jsp:attribute name="title">New Thread</jsp:attribute>
</t:newThread>