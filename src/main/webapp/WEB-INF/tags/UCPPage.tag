<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="threads" type="java.util.List"%>
<%@attribute name="posts" type="java.util.List"%>
<%@attribute name="user" type="tinf13b4.forum.model.User"%>
<%@attribute name="success" type="String"%>
<%@attribute name="error" type="String"%>
<%@attribute name="title" fragment="true"%>

<jsp:useBean id="consumer" class="tinf13b4.forum.beans.ConsumerBean" scope="request" />
<jsp:useBean id="navigation" class="tinf13b4.forum.beans.NavigationBean" scope="request" />
<jsp:useBean id="userSession" class="tinf13b4.forum.beans.SessionBean" scope="session" />

<jsp:setProperty name="navigation" property="category" value="ucp" />
<jsp:setProperty name="navigation" property="page" value="user" />

<t:genericPage>
    <jsp:attribute name="title"><jsp:invoke fragment="title" /></jsp:attribute>
    <jsp:attribute name="header"><t:header /></jsp:attribute>
    <jsp:body>
    <c:if test="${success eq true and empty error}">
   		<div class="success">User successfully updated.</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="alert">${error}</div>
    </c:if>
    <div id="user" class="ucp">
        <div id="userBlock">
            <header>
                <h2>${user.name}</h2>
            </header>
            <form method="POST" enctype="multipart/form-data" action="/api/ucp">
	            <div class="userPictureHolder" style="${not empty user.picture ? 'background-image: url(\'/uploads/'.concat(user.picture).concat('\')') : 'background-image: url(\'/img/user.gif\')' }"></div>
	            <input type="file" onchange="this.form.submit();" accept="image/*" style="display:none" name="localPicturePath"/>
	            <input type="text" name="userId" style="display:none" value="${userSession.user.id }" />
	            <div id="posts">
	                <img src="./img/quill16.png" alt=""> You have <b>${user.postCount}</b> posts
	            </div>
            </form>
        </div>
        <div class="ucpInputs">
        <form method="POST" enctype="multipart/form-data" action="/api/ucp">
            <input type="text" name="userId" style="display:none" value="${userSession.user.id }" />
            <div class="inputHolder">
                <label for="mail">Current Email</label>
                <p>${user.mail}</p>
                <label for="mail">New Email</label>
                <input type="text" id="mail" name="mail" />
                <label for="password">New Password</label>
                <input type="password" id="password" name="password"/>
                <label for="confirmPassword">Password Confirmation</label>
                <input type="password" id="confirmPassword" name="passwordConfirmation" hidden="true"/>
                <button type="submit">Update</button>
            </div>
        </form>
        </div>
        <div style="clear:both"></div>
    </div>
    </jsp:body>
</t:genericPage>
